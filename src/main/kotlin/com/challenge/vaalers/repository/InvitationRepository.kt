package com.challenge.vaalers.repository

import com.challenge.vaalers.model.Invitation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InvitationRepository : JpaRepository<Invitation, Long>
