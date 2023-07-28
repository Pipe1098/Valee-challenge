package com.challenge.vaalers.service

import com.challenge.vaalers.model.Invitation
import com.challenge.vaalers.repository.InvitationRepository
import com.opencsv.CSVReader
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

@Service
class InvitationService (private val invitationRepository: InvitationRepository){
    fun loadInvitationsFromFile(file: MultipartFile): List<Invitation> {
        val invitations: MutableList<Invitation> = mutableListOf()


        CSVReader(InputStreamReader(file.inputStream, StandardCharsets.UTF_8)).use { csvReader ->

            csvReader.skip(1)
            csvReader.forEach { line ->

                val supplierId = line[0].toInt()
                val commerceCellPhone = line[1]

                val invitation = Invitation(-1L,supplierId, commerceCellPhone)
                invitations.add(invitation)
                invitationRepository.saveAll(invitations)
            }
        }

        invitationRepository.saveAll(invitations)

        return invitations
    }

    fun getAll(): List<Invitation> {
        return invitationRepository.findAll()
    }
}