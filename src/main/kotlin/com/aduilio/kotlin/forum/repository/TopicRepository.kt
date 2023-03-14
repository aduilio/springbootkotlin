package com.aduilio.kotlin.forum.repository

import com.aduilio.kotlin.forum.model.Topic
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Provides the access to the database table topics.
 */
interface TopicRepository : JpaRepository<Topic, Long> {

    /**
     * Returns all topic for a specific course.
     *
     * @param name the course name
     * @return List of Topic
     */
    fun findByCourseName(name: String): List<Topic>
}