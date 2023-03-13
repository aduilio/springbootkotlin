package com.aduilio.kotlin.forum.repository

import com.aduilio.kotlin.forum.model.Student
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Provides the access to the database table students.
 */
interface StudentRepository : JpaRepository<Student, Long> {
}