package com.aduilio.kotlin.forum.entity

import jakarta.persistence.*
import java.time.LocalDateTime

/**
 * Represents a topic.
 */
@Entity
@Table(name = "topics")
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
        var author: User = User(),

        @Enumerated(value = EnumType.STRING)
        val status: TopicStatus = TopicStatus.NEW,

        @OneToMany(mappedBy = "topic")
        val answers: List<Answer> = ArrayList()
)
