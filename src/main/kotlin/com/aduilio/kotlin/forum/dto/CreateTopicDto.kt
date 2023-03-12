package com.aduilio.kotlin.forum.dto

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

/**
 * Represents the request to create a topic.
 */
data class CreateTopicDto(
        @field:NotEmpty
        val title: String,

        @field:NotBlank
        val message: String,

        @Valid
        val course: CreateTopicDtoCourse,

        @Valid
        val author: CreateTopicDtoAuthor
)

data class CreateTopicDtoCourse(
        @field:NotNull
        val id: Long
)

data class CreateTopicDtoAuthor(
        @field:NotNull
        val id: Long
)
