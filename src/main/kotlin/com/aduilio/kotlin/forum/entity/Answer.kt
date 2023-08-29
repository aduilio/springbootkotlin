package com.aduilio.kotlin.forum.entity

import jakarta.persistence.*
import java.time.LocalDateTime

/**
 * Represents an Answer
 */
@Entity
@Table(name = "answers")
data class Answer(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        val message: String,
        val date: LocalDateTime = LocalDateTime.now(),

        @ManyToOne
        val author: User,

        @ManyToOne
        val topic: Topic,

        val solutionAccepted: Boolean
)
