package com.aduilio.kotlin.forum.entity

import jakarta.persistence.*

/**
 * Represents a Course.
 */
@Entity
@Table(name = "courses")
data class Course(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        val name: String = "",
        val category: String = ""
)
