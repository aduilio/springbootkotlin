package com.aduilio.kotlin.forum.mapper

import com.aduilio.kotlin.forum.dto.*
import com.aduilio.kotlin.forum.entity.Course
import com.aduilio.kotlin.forum.entity.User
import com.aduilio.kotlin.forum.entity.Topic
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
                author = User(
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
                course = mapReadTopicDtoCourseFrom(topic.course),
                author = mapReadTopicDtoAuthor(topic.author)
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

    private fun mapReadTopicDtoCourseFrom(course: Course): ReadTopicDtoCourse {
        return ReadTopicDtoCourse(course.id!!, course.name, course.category)
    }

    private fun mapReadTopicDtoAuthor(author: User): ReadTopicDtoAuthor {
        return ReadTopicDtoAuthor(author.id!!, author.name, author.email)
    }
}