package com.aduilio.kotlin.forum.dto

/**
 * Represents the response to read a topic.
 */
data class ReadTopicDto(
        val id: Long,
        val title: String,
        val message: String,
        val course: ReadTopicDtoCourse,
        val author: ReadTopicDtoAuthor
)

data class ReadTopicDtoCourse(
        val id: Long,
        val name: String = "",
        val category: String = ""
)

data class ReadTopicDtoAuthor(
        val id: Long,
        val name: String = "",
        val email: String = ""
)
