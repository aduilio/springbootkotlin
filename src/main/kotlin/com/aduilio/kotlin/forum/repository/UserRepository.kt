package com.aduilio.kotlin.forum.repository

import com.aduilio.kotlin.forum.entity.User
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Provides the access to the database table user.
 */
interface UserRepository : JpaRepository<User, Long> {

    /**
     * Finds a user by e-mail.
     *
     * @param email the email
     * @return User
     */
    fun findByEmail(email: String?): User?
}