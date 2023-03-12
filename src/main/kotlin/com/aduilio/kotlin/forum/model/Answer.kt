package com.aduilio.kotlin.forum.model

import java.time.LocalDateTime

/**
 * Represents an Answer
 */
data class Answer(
        val id: Long? = null,
        val message: String,
        val date: LocalDateTime = LocalDateTime.now(),
        val author: Student,
        val topic: Topic,
        val solutionAccepted: Boolean
)
