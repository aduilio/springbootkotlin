package com.aduilio.kotlin.forum.model

import jakarta.persistence.*
import java.time.LocalDateTime

/**
 * Represents a topic.
 */
@Entity
data class Topic(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        var title: String,
        var message: String,
        val date: LocalDateTime = LocalDateTime.now(),

        @ManyToOne
        val course: Course,

        @ManyToOne
        val author: Student,

        @Enumerated(value = EnumType.STRING)
        val status: TopicStatus = TopicStatus.NEW,

        @OneToMany(mappedBy = "topic")
        val answers: List<Answer> = ArrayList()
)
