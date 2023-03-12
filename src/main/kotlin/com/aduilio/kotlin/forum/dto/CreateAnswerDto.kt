package com.aduilio.kotlin.forum.dto

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

/**
 * Represents the request to create an answer.
 */
data class CreateAnswerDto(
        @field:NotBlank
        val message: String,

        @Valid
        val topic: CreateAnswerDtoTopic,

        @Valid
        val author: CreateAnswerDtoAuthor
)

data class CreateAnswerDtoTopic(
        @field:NotNull
        val id: Long
)

data class CreateAnswerDtoAuthor(
        @field:NotNull
        val id: Long
)
