package com.aduilio.kotlin.forum.dto

/**
 * Represents the response to list answers.
 */
data class ListAnswerDto(
        val id: Long,
        val title: String,
        var author: String
)
