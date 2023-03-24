package com.aduilio.kotlin.forum.security

import com.aduilio.kotlin.forum.service.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.impl.DefaultClaims
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*
import javax.crypto.SecretKey

/**
 * Util methods to handle token.
 */
@Component
class JwtUtil(private val userService: UserService) {

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    fun generateToken(username: String, authorities: MutableCollection<out GrantedAuthority>): String? {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", authorities)
                .setExpiration(Date.from(LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.UTC)))
                .signWith(getKey(), SignatureAlgorithm.HS512)
                .compact()
    }

    fun isValid(token: String?): Boolean {
        return try {
            Jwts.parserBuilder().setSigningKey(getKey()).build().parse(token)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getAuthentication(token: String?): UsernamePasswordAuthenticationToken {
        val username = (Jwts.parserBuilder().setSigningKey(getKey()).build().parse(token).body as DefaultClaims).subject
        val user = userService.loadUserByUsername(username)
        return UsernamePasswordAuthenticationToken(username, null, user.authorities)
    }

    private fun getKey(): SecretKey? {
        val keyBytes = Decoders.BASE64.decode(secret)
        return Keys.hmacShaKeyFor(keyBytes)
    }
}