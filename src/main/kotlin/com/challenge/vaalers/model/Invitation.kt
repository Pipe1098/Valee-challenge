package com.challenge.vaalers.model


import lombok.NoArgsConstructor
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@NoArgsConstructor
data class Invitation(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val commerceCellPhone: String = "",
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    var supplier: Supplier? = null,
    val entryDate: LocalDateTime = LocalDateTime.now()

)
