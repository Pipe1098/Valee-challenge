package com.challenge.vaalers.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class ApiRequestException(message: String) : RuntimeException(message)