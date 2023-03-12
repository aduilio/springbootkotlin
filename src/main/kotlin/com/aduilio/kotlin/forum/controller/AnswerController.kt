package com.aduilio.kotlin.forum.controller

import com.aduilio.kotlin.forum.model.Answer
import com.aduilio.kotlin.forum.service.AnswerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Receives the request for the resource /topics.
 */
@RestController
@RequestMapping("/topics/{topicId}/answers")
class AnswerController(private var answerService: AnswerService) {

    /**
     * Returns all the answers for a topic.
     *
     * @param topicId the topic id
     */
    @GetMapping
    fun list(@PathVariable topicId: Long): ResponseEntity<List<Answer>> {
        return ResponseEntity.ok(answerService.list(topicId))
    }
}