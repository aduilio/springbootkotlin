package com.aduilio.kotlin.forum.repository

import com.aduilio.kotlin.forum.model.Answer
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Provides the access to the database table answers.
 */
interface AnswerRepository : JpaRepository<Answer, Long> {
}