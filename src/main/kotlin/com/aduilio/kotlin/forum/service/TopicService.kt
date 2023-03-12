package com.aduilio.kotlin.forum.service

import com.aduilio.kotlin.forum.dto.CreateTopicDto
import com.aduilio.kotlin.forum.exception.NotFoundException
import com.aduilio.kotlin.forum.mapper.TopicMapper
import com.aduilio.kotlin.forum.model.Course
import com.aduilio.kotlin.forum.model.Student
import com.aduilio.kotlin.forum.model.Topic
import org.springframework.stereotype.Service

/**
 * Provides services related to topics.
 */
@Service
class TopicService(private var topics: ArrayList<Topic>,
                   private var topicMapper: TopicMapper) {

    companion object {
        const val NOT_FOUND_MESSAGE = "Topic not found"
    }

    init {
        val topic1 = Topic(
                id = 1,
                title = "Title 1",
                message = "Message 1",
                author = Student(
                        id = 1,
                        name = "Student 1",
                        email = "st1@st.com",

                        ),
                course = Course(
                        id = 1,
                        name = "Course 1",
                        category = "Category 1",
                )
        )

        val topic2 = Topic(
                id = 2,
                title = "Title 2",
                message = "Message 2",
                author = Student(
                        id = 2,
                        name = "Student 2",
                        email = "st2@st.com",

                        ),
                course = Course(
                        id = 2,
                        name = "Course 2",
                        category = "Category 2",
                )
        )

        topics = arrayListOf(topic1, topic2)
    }

    /**
     * Creates a topic.
     *
     * @param topic to be created
     * @return Topic
     */
    fun create(createTopicDto: CreateTopicDto): Topic {
        val topic = topicMapper.mapTopicFrom(createTopicDto)
        topic.id = (topics.size.toLong() + 1)
        topics.add(topic)

        return topic
    }

    /**
     * Updates a topic.
     *
     * @param topic to be updated
     * @exception NotFoundException if the id is invalid
     */
    fun update(topic: Topic) {
        val item = topics.stream()
                .filter { item -> item.id == topic.id }
                .findFirst()
                .orElseThrow { NotFoundException(NOT_FOUND_MESSAGE) }
        topics.remove(item)
        item.title = topic.title
        item.message = topic.message
        topics.add(item)
    }

    /**
     * Returns all topics.
     *
     * @return List of Topic
     */
    fun list(): List<Topic> {
        return topics
    }

    /**
     * Returns the topic by id
     *
     * @param id the topic id
     * @return Topic
     * @exception NotFoundException if the id is invalid
     */
    fun read(id: Long): Topic? {
        return topics.stream()
                .filter { topic -> topic.id == id }
                .findFirst()
                .orElseThrow { NotFoundException(NOT_FOUND_MESSAGE) }
    }

    /**
     * Deletes a topic.
     *
     * @param id the topic id
     * @exception NotFoundException if the id is invalid
     */
    fun delete(id: Long) {
        val topic = topics.stream()
                .filter { topic -> topic.id == id }
                .findFirst()
                .orElseThrow { NotFoundException(NOT_FOUND_MESSAGE) }
        topics.remove(topic)
    }
}