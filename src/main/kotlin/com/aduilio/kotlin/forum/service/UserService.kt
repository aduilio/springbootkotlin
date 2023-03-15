package com.aduilio.kotlin.forum.service

import com.aduilio.kotlin.forum.exception.UnauthorizedException
import com.aduilio.kotlin.forum.security.UserDetailsImpl
import com.aduilio.kotlin.forum.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

/**
 * Provides services related to user.
 */
@Service
class UserService(private val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository.findByEmail(username) ?: throw UnauthorizedException("E-mail invalid.")
        return UserDetailsImpl(user)
    }
}