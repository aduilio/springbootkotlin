package com.aduilio.kotlin.forum.service

import com.aduilio.kotlin.forum.dto.CreateTopicDto
import com.aduilio.kotlin.forum.dto.ListTopicDto
import com.aduilio.kotlin.forum.dto.ReadTopicDto
import com.aduilio.kotlin.forum.dto.UpdateTopicDto
import com.aduilio.kotlin.forum.exception.NotFoundException
import com.aduilio.kotlin.forum.mapper.TopicMapper
import com.aduilio.kotlin.forum.model.Topic
import com.aduilio.kotlin.forum.repository.TopicRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors

/**
 * Provides services related to topics.
 */
@Service
class TopicService(private val topicRepository: TopicRepository,
                   private val topicMapper: TopicMapper) {

    companion object {
        const val NOT_FOUND_MESSAGE = "Topic not found"
    }

    /**
     * Creates a topic.
     *
     * @param createTopicDto the information to be created
     * @return ReadTopicDto
     */
    fun create(createTopicDto: CreateTopicDto): ReadTopicDto {
        val topic = topicMapper.mapTopicFrom(createTopicDto)
        val result = topicRepository.save(topic)

        return topicMapper.mapReadTopicDtoFrom(result)
    }

    /**
     * Updates a topic.
     *
     * @param id to topic id
     * @param updateTopicDto the information to be updated
     * @return ReadTopicDto
     * @exception NotFoundException if the id is invalid
     */
    fun update(id: Long, updateTopicDto: UpdateTopicDto): ReadTopicDto {
        val topic = readTopic(id)
        topicMapper.mapTopicFrom(updateTopicDto, topic)

        return topicMapper.mapReadTopicDtoFrom(topic)
    }

    /**
     * Returns all topics.
     *
     * @return List of ListTopicDto
     */
    fun list(): List<ListTopicDto> {
        return topicRepository.findAll().stream()
                .map { topic -> topicMapper.mapListTopicDtoFrom(topic) }
                .collect(Collectors.toList())
    }

    /**
     * Returns the topic by id
     *
     * @param id the topic id
     * @return ReadTopicDto
     * @exception NotFoundException if the id is invalid
     */
    fun read(id: Long): ReadTopicDto {
        val topic = readTopic(id)

        return topicMapper.mapReadTopicDtoFrom(topic)
    }

    /**
     * Deletes a topic.
     *
     * @param id the topic id
     * @exception NotFoundException if the id is invalid
     */
    fun delete(id: Long) {
        topicRepository.deleteById(id)
    }

    private fun readTopic(id: Long): Topic {
        return topicRepository.findById(id)
                .orElseThrow { NotFoundException(NOT_FOUND_MESSAGE) }
    }
}