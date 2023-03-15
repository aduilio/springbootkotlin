package com.aduilio.kotlin.forum.service

import com.aduilio.kotlin.forum.entity.Answer
import com.aduilio.kotlin.forum.entity.Course
import com.aduilio.kotlin.forum.entity.User
import com.aduilio.kotlin.forum.entity.Topic
import org.springframework.stereotype.Service
import java.util.stream.Collectors

/**
 * Provides services related to topics.
 */
@Service
class AnswerService(private var answers: List<Answer>) {

    init {
        val course = Course(
                id = 1,
                name = "Course",
                category = "Category"
        )
        val author = User(
                id = 1,
                name = "Student",
                email = "st@email.com"
        )
        val topic = Topic(
                id = 1,
                title = "Topic",
                message = "Message",
                course = course,
                author = author
        )

        val answer1 = Answer(
                id = 1,
                message = "Answer 1",
                author = author,
                topic = topic,
                solutionAccepted = false
        )

        val answer2 = Answer(
                id = 2,
                message = "Answer 2",
                author = author,
                topic = topic,
                solutionAccepted = false
        )

        answers = listOf(answer1, answer2)
    }

    /**
     * Returns all answers for a topic.
     *
     * @param topicId the topic id
     */
    fun list(topicId: Long): List<Answer> {
        return answers.stream()
                .filter { answer -> answer.topic.id == topicId }
                .collect(Collectors.toList())
    }

}