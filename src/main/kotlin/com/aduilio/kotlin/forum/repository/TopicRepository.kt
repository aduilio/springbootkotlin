package com.aduilio.kotlin.forum.repository

import com.aduilio.kotlin.forum.model.Topic
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Provided the access to the database table topics.
 */
interface TopicRepository : JpaRepository<Topic, Long> {
}