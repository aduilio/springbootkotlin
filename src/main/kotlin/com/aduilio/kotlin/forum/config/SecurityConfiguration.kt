package com.aduilio.kotlin.forum.config

import com.aduilio.kotlin.forum.entity.UserRole
import com.aduilio.kotlin.forum.filter.JwtAuthenticationFilter
import com.aduilio.kotlin.forum.filter.JwtLoginFilter
import com.aduilio.kotlin.forum.security.JwtUtil
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

/**
 * Creates the beans and configuration regarding the security.
 */
@Configuration
@EnableWebSecurity
class SecurityConfiguration(private val configuration: AuthenticationConfiguration,
                            private val jwtUtil: JwtUtil,
                            private val jwtAuthenticationFilter: JwtAuthenticationFilter) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        val jwtLoginFilter = JwtLoginFilter(configuration.authenticationManager, jwtUtil)

        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/h2-console").permitAll()
                .requestMatchers(HttpMethod.GET, "/swagger-ui/*").permitAll()
                .requestMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll()
                //.requestMatchers(HttpMethod.POST, "/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/topics").hasAnyAuthority(UserRole.STUDENT.value, UserRole.TEACHER.value, UserRole.ADMIN.value)
                .requestMatchers(HttpMethod.POST, "/topics").hasAuthority(UserRole.STUDENT.value)
                .requestMatchers(HttpMethod.PATCH, "/topics").hasAuthority(UserRole.TEACHER.value)
                .requestMatchers(HttpMethod.DELETE, "/topics").hasAuthority(UserRole.ADMIN.value)
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(jwtLoginFilter, UsernamePasswordAuthenticationFilter::class.java)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .build()
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}