package com.challenge.vaalers.controller

import com.challenge.vaalers.model.Invitation
import com.challenge.vaalers.service.InvitationService
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1/invitation")
class FileController (private val invitationService: InvitationService) {

    @PostMapping("/csv")
    fun loadInvitations(@RequestParam("archivo") file: MultipartFile) {
        val invitaciones = invitationService.loadInvitationsFromFile(file)
        // Guardar las invitaciones en la base de datos o realizar otras acciones necesarias
    }

        @GetMapping
        fun getInvitations(): List<Invitation> {
            return invitationService.getAll()
        }
}