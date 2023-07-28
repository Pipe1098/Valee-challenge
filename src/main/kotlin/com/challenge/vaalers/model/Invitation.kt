package com.challenge.vaalers.model


import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Invitation(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val supplierId: Int = 0,
    val commerceCellPhone: String = "",
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    val supplier: Supplier? = null,
    val entryDate: LocalDateTime = LocalDateTime.now()
)
{
    // Constructor vacío para JPA
    @Suppress("unused")
    @JvmOverloads
    constructor() : this(null, 0, "")
}