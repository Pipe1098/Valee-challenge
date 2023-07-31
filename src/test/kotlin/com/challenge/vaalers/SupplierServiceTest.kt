package com.challenge.vaalers

import com.challenge.vaalers.dto.request.SupplierRequest
import com.challenge.vaalers.model.Supplier
import com.challenge.vaalers.repository.SupplierRepository
import com.challenge.vaalers.service.SupplierService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class SupplierServiceTest {

    @Mock
    private lateinit var supplierRepository: SupplierRepository

    @InjectMocks
    private lateinit var supplierService: SupplierService

    init {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testCreateSupplier() {
        // Given
        val supplierRequest = SupplierRequest(name = "Test Supplier", code = "SUP123")
        val expectedSupplier = Supplier(id = 1, name = "Test Supplier", code = "SUP123")

        // Mock the behavior of the supplierRepository.save() method
        Mockito.`when`(supplierRepository.save(Mockito.any(Supplier::class.java))).thenReturn(expectedSupplier)

        // When
        val createdSupplier = supplierService.createSupplier(supplierRequest)

        // Then
        assertEquals(expectedSupplier, createdSupplier)
    }
}
