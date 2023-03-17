package com.aduilio.kotlin.forum.config

import com.aduilio.kotlin.forum.entity.UserRole
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

/**
 * Creates the beans regarding security.
 */
@Configuration
@EnableWebSecurity
class SecurityConfiguration {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http.authorizeHttpRequests()
                .requestMatchers("/h2-console").permitAll()
                .requestMatchers(HttpMethod.GET, "/topics").permitAll()
                .requestMatchers(HttpMethod.POST, "/topics").hasAuthority(UserRole.STUDENT.toString())
                .requestMatchers(HttpMethod.PATCH, "/topics").hasAuthority(UserRole.TEACHER.value)
                .requestMatchers(HttpMethod.DELETE, "/topics").hasAuthority(UserRole.ADMIN.value)
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic()
                .and()
                .build()
    }

    @Bean
    fun authenticationManager(configuration: AuthenticationConfiguration): AuthenticationManager {
        return configuration.authenticationManager
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}