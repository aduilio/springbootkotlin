package com.aduilio.kotlin.forum.security

import com.aduilio.kotlin.forum.entity.User
import org.springframework.security.core.GrantedAuthority

/**
 * Implements GrantedAuthority.
 */
class GrantedAuthorityImpl(private val user: User) : GrantedAuthority {

    override fun getAuthority() = user.role.value
}