package com.challenge.vaalers.controller

import com.challenge.vaalers.dto.response.ApiResponse
import com.challenge.vaalers.dto.request.SupplierRequest
import com.challenge.vaalers.service.SupplierService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/suppliers")
class SupplierController(private val supplierService: SupplierService) {

    @Operation(summary = "Register a new supplier")
    @PostMapping("/sup")
    fun createSupplier(@RequestBody supplierRequest: SupplierRequest): ResponseEntity<ApiResponse> {
        val supplier = supplierService.createSupplier(supplierRequest)
        val apiResponse = ApiResponse(0, "Supplier created successfully")
        return ResponseEntity.ok(apiResponse)
    }
}