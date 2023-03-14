package com.aduilio.kotlin.forum.controller

import com.aduilio.kotlin.forum.dto.CreateTopicDto
import com.aduilio.kotlin.forum.dto.ListTopicDto
import com.aduilio.kotlin.forum.dto.ReadTopicDto
import com.aduilio.kotlin.forum.dto.UpdateTopicDto
import com.aduilio.kotlin.forum.service.TopicService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

/**
 * Receives the request for the resource /topics.
 */
@RestController
@RequestMapping("/topics")
class TopicController(private val topicService: TopicService) {

    @PostMapping
    @Transactional
    @CacheEvict(value = ["topics"], allEntries = true)
    fun create(@RequestBody @Valid createTopicDto: CreateTopicDto,
               componentsBuilder: UriComponentsBuilder): ResponseEntity<ReadTopicDto> {
        val topic = topicService.create(createTopicDto)
        val uri = componentsBuilder.path("/topics/${topic.id}").build().toUri()

        return ResponseEntity.created(uri).body(topic)
    }

    @PatchMapping("/{id}")
    @Transactional
    @CacheEvict(value = ["topics"], allEntries = true)
    fun update(@PathVariable id: Long, @RequestBody @Valid updateTopicDto: UpdateTopicDto): ResponseEntity<ReadTopicDto> {
        val topic = topicService.update(id, updateTopicDto)

        return ResponseEntity.ok(topic)
    }

    @GetMapping
    @Cacheable("topics")
    fun list(@RequestParam(required = false) courseName: String?,
             @PageableDefault(size = 5, sort = ["date"], direction = Sort.Direction.DESC) pageable: Pageable): ResponseEntity<Page<ListTopicDto>> {
        val topics = topicService.list(courseName, pageable)

        return ResponseEntity.ok(topics)
    }

    @GetMapping("/{id}")
    fun read(@PathVariable id: Long): ResponseEntity<ReadTopicDto> {
        val topic = topicService.read(id)

        return ResponseEntity.ok(topic)
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = ["topics"], allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        topicService.delete(id)
    }
}