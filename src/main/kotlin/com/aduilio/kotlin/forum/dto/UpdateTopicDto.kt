package com.aduilio.kotlin.forum.dto

import jakarta.validation.constraints.NotEmpty

/**
 * Represents the request to update a topic.
 */
data class UpdateTopicDto(
        @field:NotEmpty
        val title: String,

        @field:NotEmpty
        val message: String
)

