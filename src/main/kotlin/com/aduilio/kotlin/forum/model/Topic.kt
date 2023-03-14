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

        var title: String = "",
        var message: String = "",
        val date: LocalDateTime = LocalDateTime.now(),

        @ManyToOne
        var course: Course = Course(),

        @ManyToOne
        var author: Student = Student(),

        @Enumerated(value = EnumType.STRING)
        val status: TopicStatus = TopicStatus.NEW,

        @OneToMany(mappedBy = "topic")
        val answers: List<Answer> = ArrayList()
)
