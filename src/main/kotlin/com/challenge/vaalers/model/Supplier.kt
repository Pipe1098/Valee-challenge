package com.challenge.vaalers.model

import lombok.NoArgsConstructor
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@NoArgsConstructor
class Supplier(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    val code: String?,
    val isActive: Boolean = true,
    val entryDate: LocalDateTime = LocalDateTime.now(),
)
