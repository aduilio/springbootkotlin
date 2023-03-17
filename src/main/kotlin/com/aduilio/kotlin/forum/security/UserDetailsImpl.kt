package com.aduilio.kotlin.forum.security

import com.aduilio.kotlin.forum.entity.User
import org.springframework.security.core.userdetails.UserDetails

/**
 * Implements UserDetails.
 */
class UserDetailsImpl(private val user: User) : UserDetails {

    override fun getAuthorities() = listOf(GrantedAuthorityImpl(user))

    override fun getPassword() = user.password

    override fun getUsername() = user.email

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}