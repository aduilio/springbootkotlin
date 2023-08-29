package com.aduilio.kotlin.forum.controller

import com.aduilio.kotlin.forum.dto.CreateAnswerDto
import com.aduilio.kotlin.forum.dto.ReadAnswerDto
import com.aduilio.kotlin.forum.service.AnswerService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.cache.annotation.CacheEvict
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

/**
 * Receives the request for the resource /answers.
 */
@RestController
@RequestMapping("/answers")
class AnswerController(private var answerService: AnswerService) {

    @PostMapping
    @Transactional
    @CacheEvict(value = ["answers"], allEntries = true)
    fun create(@RequestBody @Valid createAnswerDto: CreateAnswerDto,
               componentsBuilder: UriComponentsBuilder): ResponseEntity<ReadAnswerDto> {
        val answer = answerService.create(createAnswerDto)
        val uri = componentsBuilder.path("/answers/${answer.id}").build().toUri()

        return ResponseEntity.created(uri).body(answer)
    }
}