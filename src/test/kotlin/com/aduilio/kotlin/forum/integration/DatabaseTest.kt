package com.aduilio.kotlin.forum.integration

import com.aduilio.kotlin.forum.entity.Answer
import com.aduilio.kotlin.forum.entity.Course
import com.aduilio.kotlin.forum.entity.Topic
import com.aduilio.kotlin.forum.entity.User
import com.aduilio.kotlin.forum.repository.AnswerRepository
import com.aduilio.kotlin.forum.repository.CourseRepository
import com.aduilio.kotlin.forum.repository.TopicRepository
import com.aduilio.kotlin.forum.repository.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.Pageable
import org.testcontainers.junit.jupiter.Testcontainers

@DataJpaTest
@Testcontainers
class DatabaseTest {

    companion object {
        const val COURSE_NAME = "course-name"
    }

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var courseRepository: CourseRepository

    @Autowired
    private lateinit var topicRepository: TopicRepository

    @Autowired
    private lateinit var answerRepository: AnswerRepository

    @Test
    fun createUserCourseTopicAnswer() {
        val userId = createUser()
        val courseId = createCourse()
        val topicId = createTopic(userId, courseId)
        createAnswer(userId, topicId)
    }

    private fun createUser(): Long {
        val userName = "user-name"
        val userEmail = "user-email"
        val userPassword = "user-password"

        val user = User(name = userName, email = userEmail, password = userPassword)
        userRepository.save(user)

        val response = userRepository.findByEmail(userEmail)
        assertThat(response).isNotNull
        assertThat(response?.name).isEqualTo(userName)
        assertThat(response?.email).isEqualTo(userEmail)
        assertThat(response?.password).isEqualTo(userPassword)

        return response?.id!!
    }

    private fun createCourse(): Long {
        val courseCategory = "course-category"

        val course = Course(name = COURSE_NAME, category = courseCategory)
        val response = courseRepository.save(course)

        assertThat(response.name).isEqualTo(COURSE_NAME)
        assertThat(response.category).isEqualTo(courseCategory)

        return response.id!!
    }

    private fun createTopic(userId: Long, courseId: Long): Long {
        val topicTitle = "topic-title"
        val topicMessage = "topic-message"

        val topic = Topic(course = Course(id = courseId), title = topicTitle, message = topicMessage, author = User(id = userId))
        topicRepository.save(topic)

        val response = topicRepository.findByCourseName(COURSE_NAME, Pageable.ofSize(1))
        assertThat(response).hasSize(1)
        response.get().forEach {
            assertThat(it.title).isEqualTo(topicTitle)
            assertThat(it.message).isEqualTo(topicMessage)
            assertThat(it.author.id).isEqualTo(userId)
            assertThat(it.course.id).isEqualTo(courseId)
        }

        return response.get().findFirst().get().id!!
    }

    private fun createAnswer(userId: Long, topicId: Long) {
        val answerMessage = "answer-message"

        val answer = Answer(message = answerMessage, author = User(id = userId), topic = Topic(id = topicId), solutionAccepted = true)
        val response = answerRepository.save(answer)

        assertThat(response.message).isEqualTo(answerMessage)
        assertThat(response.date).isNotNull()
        assertThat(response.author.id).isEqualTo(userId)
        assertThat(response.topic.id).isEqualTo(topicId)
        assertThat(response.solutionAccepted).isTrue()
    }
}