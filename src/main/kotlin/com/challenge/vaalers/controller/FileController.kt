package com.challenge.vaalers.controller

import com.challenge.vaalers.dto.response.ApiResponse
import com.challenge.vaalers.dto.response.ApiResponseWithList
import com.challenge.vaalers.service.InvitationService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.models.media.Content
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1/invitations")
class FileController (private val invitationService: InvitationService) {

    @PostMapping("")
    fun loadInvitations(@RequestParam("file") file: MultipartFile) {
        val invitaciones = invitationService.loadInvitationsFromFile(file)

    }

        @GetMapping("")
        @Operation(method = "Shows the list of existing invitations in the database")
        fun getAllInvitaciones(): ResponseEntity<ApiResponseWithList> {
            val invitationResponses = invitationService.getAllInvitationsWithSupplierInfo()
            val apiResponse = ApiResponse(0, "Success")
            val response = ApiResponseWithList(apiResponse, invitationResponses)
            return ResponseEntity.ok(response)
        }
}