package com.aduilio.kotlin.forum.entity

import jakarta.persistence.*

/**
 * Represents an user.
 */
@Entity
@Table(name = "users")
data class User(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        val name: String = "",
        val email: String = "",
        val password: String = "",

        @Enumerated(value = EnumType.STRING)
        val role: UserRole = UserRole.NONE
)
