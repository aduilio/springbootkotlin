package com.aduilio.kotlin.forum.filter

import com.aduilio.kotlin.forum.security.JwtUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

/**
 * Validates if the user is authenticated.
 */
@Component
class JwtAuthenticationFilter(private val jwtUtil: JwtUtil) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val token = getTokenInfo(request.getHeader("Authorization"))
        if (jwtUtil.isValid(token)) {
            val authentication = jwtUtil.getAuthentication(token)
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }

    private fun getTokenInfo(token: String?): String? {
        return token?.let {
            it.substring(7, it.length)
        }
    }
}
