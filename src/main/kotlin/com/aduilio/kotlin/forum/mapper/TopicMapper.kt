package com.aduilio.kotlin.forum.mapper

import com.aduilio.kotlin.forum.dto.*
import com.aduilio.kotlin.forum.model.Course
import com.aduilio.kotlin.forum.model.Student
import com.aduilio.kotlin.forum.model.Topic
import org.springframework.stereotype.Component

/**
 * Maps the attributes from/to DTOs.
 */
@Component
class TopicMapper {

    /**
     * Maps a CreateTopicDto to Topic.
     *
     * @param createTopicDto to be mapped
     * @return Topic
     */
    fun mapTopicFrom(createTopicDto: CreateTopicDto): Topic {
        return Topic(
                title = createTopicDto.title,
                message = createTopicDto.message,
                course = Course(
                        id = createTopicDto.course.id
                ),
                author = Student(
                        id = createTopicDto.author.id
                )
        )
    }

    /**
     * Maps a UpdateTopicDto to Topic.
     *
     * @param updateTopicDto to be mapped
     * @param topic with the result of the map
     */
    fun mapTopicFrom(updateTopicDto: UpdateTopicDto, topic: Topic) {
        topic.message = updateTopicDto.message
        topic.title = updateTopicDto.title
    }

    /**
     * Maps a Topic to ReadTopicDto.
     *
     * @param topic to be mapped
     * @return ReadTopicDto
     */
    fun mapReadTopicDtoFrom(topic: Topic): ReadTopicDto {
        return ReadTopicDto(
                id = topic.id!!,
                title = topic.title,
                message = topic.message,
                course = ReadTopicDtoCourse(
                        id = topic.course.id!!
                ),
                author = ReadTopicDtoAuthor(
                        id = topic.author.id!!
                )
        )
    }

    /**
     * Maps a Topic to ListTopicDto.
     *
     * @param topic to be mapped
     * @return ListTopicDto
     */
    fun mapListTopicDtoFrom(topic: Topic): ListTopicDto {
        return ListTopicDto(
                id = topic.id!!,
                title = topic.title
        )
    }
}