package com.aduilio.kotlin.forum.repository

import com.aduilio.kotlin.forum.dto.TopicCategoryReport
import com.aduilio.kotlin.forum.entity.Topic
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

/**
 * Provides the access to the database table topics.
 */
interface TopicRepository : JpaRepository<Topic, Long> {

    /**
     * Returns all topic for a specific course.
     *
     * @param name the course name
     * @param pageable pagination information
     * @return List of Topic
     */
    fun findByCourseName(name: String, pageable: Pageable): Page<Topic>

    /**
     * Returns the quantity of topics by category.
     *
     * List of TopicCategoryReport
     */
    @Query("SELECT new com.aduilio.kotlin.forum.dto.TopicCategoryReport(course.category, count(t)) FROM Topic t JOIN t.course course GROUP BY course.category")
    fun report(): List<TopicCategoryReport>
}