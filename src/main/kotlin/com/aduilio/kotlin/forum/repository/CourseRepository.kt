package com.aduilio.kotlin.forum.repository

import com.aduilio.kotlin.forum.entity.Course
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Provides the access to the database table courses.
 */
interface CourseRepository : JpaRepository<Course, Long> {
}