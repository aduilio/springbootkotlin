package com.aduilio.kotlin.forum.model

import java.time.LocalDateTime

/**
 * Represents a Topic.
 */
data class Topic(
        val id: Long? = null,
        val title: String,
        val message: String,
        val date: LocalDateTime = LocalDateTime.now(),
        val course: Course,
        val author: Student,
        val status: TopicStatus = TopicStatus.NEW,
        val answers: List<Answer> = ArrayList()
)
