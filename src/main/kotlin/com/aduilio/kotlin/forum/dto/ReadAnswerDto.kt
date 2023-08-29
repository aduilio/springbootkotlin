package com.aduilio.kotlin.forum.dto

import java.time.LocalDateTime

/**
 * Represents the response to read an answer.
 */
data class ReadAnswerDto(
        val id: Long,
        val message: String,
        val date: LocalDateTime,
        val topic: ReadAnswerDtoTopic? = null,
        val author: ReadAnswerDtoAuthor? = null,
        val accepted: Boolean
)

data class ReadAnswerDtoTopic(
        val id: Long,
        val name: String = "",
        val category: String = ""
)

data class ReadAnswerDtoAuthor(
        val id: Long,
        val name: String = "",
        val email: String = ""
)
