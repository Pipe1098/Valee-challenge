package com.challenge.vaalers.service


import com.challenge.vaalers.dto.request.SupplierRequest
import com.challenge.vaalers.model.Supplier
import com.challenge.vaalers.repository.SupplierRepository
import org.springframework.stereotype.Service

@Service
class SupplierService(private val supplierRepository: SupplierRepository) {

    fun createSupplier(supplierRequest: SupplierRequest): Supplier {
        val supplier = Supplier(
            name = supplierRequest.name,
            code = supplierRequest.code,
        )
        return supplierRepository.save(supplier)
    }
}






