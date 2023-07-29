package com.challenge.vaalers.dto.response

data class ApiResponseWithList(
    val apiResponse: ApiResponse,
    val list: List<InvitationResponse>
)