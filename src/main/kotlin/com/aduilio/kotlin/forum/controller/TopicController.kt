package com.aduilio.kotlin.forum.controller

import com.aduilio.kotlin.forum.dto.CreateTopicDto
import com.aduilio.kotlin.forum.dto.ListTopicDto
import com.aduilio.kotlin.forum.dto.ReadTopicDto
import com.aduilio.kotlin.forum.dto.UpdateTopicDto
import com.aduilio.kotlin.forum.mapper.TopicMapper
import com.aduilio.kotlin.forum.model.Topic
import com.aduilio.kotlin.forum.service.TopicService
import jakarta.validation.Valid
import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI
import java.util.stream.Collectors

/**
 * Receives the request for the resource /topics.
 */
@RestController
@RequestMapping("/topics")
class TopicController(private val topicService: TopicService,
                      private val topicMapper: TopicMapper) {

    @PostMapping
    fun create(@RequestBody @Valid topic: CreateTopicDto,
               componentsBuilder: UriComponentsBuilder): ResponseEntity<ReadTopicDto> {
        val result = topicService.create(topic)
        val response = topicMapper.mapReadTopicDtoFrom(result)
        val uri = componentsBuilder.path("/topics/${result.id}").build().toUri()
        return ResponseEntity.created(uri).body(response)
    }

    @PatchMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody @Valid updateTopicDto: UpdateTopicDto): ResponseEntity<ReadTopicDto> {
        val topic = topicService.read(id)
        topicMapper.mapTopicFrom(updateTopicDto, topic!!)
        topicService.update(topic)

        return ResponseEntity.ok(topicMapper.mapReadTopicDtoFrom(topic))
    }

    @GetMapping
    fun list(): ResponseEntity<List<ListTopicDto>> {
        val topics = topicService.list()
        val response = topics.stream()
                .map { topic -> topicMapper.mapListTopicDtoFrom(topic) }
                .collect(Collectors.toList())
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun read(@PathVariable id: Long): ResponseEntity<ReadTopicDto> {
        val result = topicService.read(id)
        val response = topicMapper.mapReadTopicDtoFrom(result!!)
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        topicService.delete(id)
    }
}