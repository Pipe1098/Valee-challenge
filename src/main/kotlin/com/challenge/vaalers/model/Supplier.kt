package com.challenge.vaalers.model

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Supplier(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    val code: String?,
    val isActive: Boolean = true,
    val entryDate: LocalDateTime = LocalDateTime.now()
)
