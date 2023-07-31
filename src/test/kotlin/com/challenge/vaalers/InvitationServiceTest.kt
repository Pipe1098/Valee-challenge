package com.challenge.vaalers

import com.challenge.vaalers.exception.ApiRequestException
import com.challenge.vaalers.model.Invitation
import com.challenge.vaalers.model.Supplier
import com.challenge.vaalers.repository.InvitationRepository
import com.challenge.vaalers.repository.SupplierRepository
import com.challenge.vaalers.service.InvitationService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.*
import org.springframework.mock.web.MockMultipartFile
import java.io.ByteArrayInputStream

class InvitationServiceTest {

    @Mock
    private lateinit var invitationRepository: InvitationRepository

    @Mock
    private lateinit var supplierRepository: SupplierRepository

    @InjectMocks
    private lateinit var invitationService: InvitationService

    init {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testLoadInvitationsFromFile_ValidFile() {
        // Given
        val csvContent = "2,+12345678901\n3,+98765432101\n"
        val file = MockMultipartFile("test.csv", ByteArrayInputStream(csvContent.toByteArray()))

        val supplier1 = Supplier(2L, "Supplier 1", "SUP01")
        val supplier2 = Supplier(3L, "Supplier 2", "SUP02")

        Mockito.`when`(supplierRepository.findById(2L)).thenReturn(java.util.Optional.of(supplier1))
        Mockito.`when`(supplierRepository.findById(3L)).thenReturn(java.util.Optional.of(supplier2))

        // Capture the argument passed to invitationRepository.saveAll
        val invitationCaptor = ArgumentCaptor.forClass(List::class.java) as ArgumentCaptor<List<Invitation>>

        // When
        val result = invitationService.loadInvitationsFromFile(file)

        // Then
        // Verify that saveAll was called with the correct list of invitations
        Mockito.verify(invitationRepository).saveAll(invitationCaptor.capture())
        Assertions.assertEquals(1, invitationCaptor.value.size)


    }

    @Test
    fun testLoadInvitationsFromFile_InvalidFile() {
        // Given
        val csvContent = "2,+12345678902\n4,+98765432102\n"
        val file = MockMultipartFile("test.csv", ByteArrayInputStream(csvContent.toByteArray()))

        val supplier1 = Supplier(2L, "Supplier 1", "SUP01")
        Mockito.`when`(supplierRepository.findById(2L)).thenReturn(java.util.Optional.of(supplier1))

        // When & Then
        val exception = Assertions.assertThrows(ApiRequestException::class.java) {
            invitationService.loadInvitationsFromFile(file)
        }
        Assertions.assertEquals("no hay un Proveedor con el supplierId: 4 en la linea 2 del archivo csv ", exception.message)
    }


}
