package com.challenge.vaalers.service

import com.challenge.vaalers.dto.response.InvitationResponse
import com.challenge.vaalers.exception.ApiRequestException
import com.challenge.vaalers.model.Invitation
import com.challenge.vaalers.model.Supplier
import com.challenge.vaalers.repository.InvitationRepository
import com.challenge.vaalers.repository.SupplierRepository
import com.opencsv.CSVReader
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.time.LocalDateTime

@Service
class InvitationService (
    private val invitationRepository: InvitationRepository,
    private val supplierRepository: SupplierRepository
){
    fun loadInvitationsFromFile(file: MultipartFile): List<Invitation> {
        val invitations: MutableList<Invitation> = mutableListOf()

        CSVReader(InputStreamReader(file.inputStream, StandardCharsets.UTF_8)).use { csvReader ->
            csvReader.skip(1)
            csvReader.forEachIndexed { index, line ->
                if (line.size == 2) {
                    try {
                        val supplierId = line[0].toLong()
                        val commerceCellPhone = line[1]

                        // Validar que supplierId sea un número válido (entero)
                        if (supplierId <= 0) {
                            throw ApiRequestException("El campo supplierId en la línea ${index + 2} debe ser un número entero positivo.")
                        }

                        // Validar que el número de teléfono tenga un formato válido
                        if (!isValidPhoneNumber(commerceCellPhone)) {
                            throw ApiRequestException("El campo commerceCellPhone en la línea ${index + 2} no es un número de teléfono válido.")
                        }

                        val supplier: Supplier = supplierRepository.findById(supplierId).orElse(null)
                            ?: throw ApiRequestException("no hay un Proveedor con el supplierId: $supplierId en la linea ${index + 2} del archivo csv ")

                        val invitation = Invitation(-1L, commerceCellPhone, supplier, LocalDateTime.now())
                        invitations.add(invitation)
                    } catch (e: NumberFormatException) {
                        throw ApiRequestException("El campo supplierId en la línea ${index + 2} debe ser un número entero.")
                    }
                } else {
                    throw ApiRequestException("La línea ${index + 2} del archivo debe tener exactamente dos campos.")
                }
            }
        }

        return invitationRepository.saveAll(invitations)
    }


    fun isValidPhoneNumber(phoneNumber: String): Boolean {
        val phoneRegex = """\+\d{1,2}\d{10}""".toRegex()
        return phoneRegex.matches(phoneNumber)
    }

    fun getAll(): List<Invitation> {
        return invitationRepository.findAll()
    }

    fun getAllInvitationsWithSupplierInfo(): List<InvitationResponse> {
        val invitations = invitationRepository.findAll()

        return invitations.map { invitation ->
            val supplier = invitation.supplier // Obtiene el proveedor asociado a la invitación
            val supplierId = supplier?.id ?: 0
            val supplierName = supplier?.name ?: ""

            InvitationResponse(
                supplierId = supplierId,
                supplierName = supplierName,
                commerceCellPhone = invitation.commerceCellPhone
            )
        }
    }
}