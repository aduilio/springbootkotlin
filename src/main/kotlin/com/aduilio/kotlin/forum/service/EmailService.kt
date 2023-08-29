package com.aduilio.kotlin.forum.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(private val javaMailSender: JavaMailSender) {

    fun send(emailTo: String) {
        val message = SimpleMailMessage()
        message.subject = "New answer"
        message.text = "Your topic has a new answer."
        message.setTo(emailTo)

        javaMailSender.send(message)
    }
}