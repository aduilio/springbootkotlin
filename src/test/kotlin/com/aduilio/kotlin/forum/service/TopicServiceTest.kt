package com.aduilio.kotlin.forum.service

import com.aduilio.kotlin.forum.dto.CreateTopicDto
import com.aduilio.kotlin.forum.dto.ListTopicDto
import com.aduilio.kotlin.forum.dto.ReadTopicDto
import com.aduilio.kotlin.forum.dto.UpdateTopicDto
import com.aduilio.kotlin.forum.entity.Topic
import com.aduilio.kotlin.forum.exception.NotFoundException
import com.aduilio.kotlin.forum.mapper.TopicMapper
import com.aduilio.kotlin.forum.repository.TopicRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.util.*

/**
 * Tests for TopicServiceTest.
 */
class TopicServiceTest {

    @MockK
    private lateinit var topicRepositoryMock: TopicRepository

    @MockK
    private lateinit var topicMapperMock: TopicMapper

    @InjectMockKs
    private lateinit var topicService: TopicService

    @BeforeEach
    fun setup(){
        MockKAnnotations.init(this)
    }

    @Test
    fun createWithValueShouldReturnResponse() {
        val topic = mockk<CreateTopicDto>()
        val topicMapped = mockk<Topic>()
        val topicResult = mockk<ReadTopicDto>()

        every { topicMapperMock.mapTopicFrom(topic) } returns topicMapped
        every { topicRepositoryMock.save(topicMapped) } returns topicMapped
        every { topicMapperMock.mapReadTopicDtoFrom(topicMapped) } returns topicResult

        val result = topicService.create(topic)

        assertThat(result).isEqualTo(topicResult)
    }

    @Test
    fun updateWithValueShouldReturnResponse() {
        val id = 1L
        val topic = mockk<UpdateTopicDto>()
        val topicRead = mockk<Topic>()
        val topicResult = mockk<ReadTopicDto>()

        every { topicRepositoryMock.findById(id) } returns Optional.of(topicRead)
        every { topicMapperMock.mapTopicFrom(topic, topicRead) } returns Unit
        every { topicMapperMock.mapReadTopicDtoFrom(topicRead) } returns topicResult

        val result = topicService.update(id, topic)

        assertThat(result).isEqualTo(topicResult)
        verify(exactly = 1) { topicMapperMock.mapTopicFrom(topic, topicRead) }
    }

    @Test
    fun updateWithInvalidIdShouldThrowException() {
        val id = 1L
        val topic = mockk<UpdateTopicDto>()

        every { topicRepositoryMock.findById(id) } returns Optional.empty()

        val exception = assertThrows<NotFoundException> { topicService.update(id, topic) }

        assertThat(exception.message).isEqualTo("Topic not found")
    }

    @Test
    fun listWithoutCourseShouldReturnAll() {
        val pageable = mockk<Pageable>()
        val page = PageImpl(listOf(Topic()))

        every { topicRepositoryMock.findAll(pageable) } returns page
        every { topicMapperMock.mapListTopicDtoFrom(any()) } returns ListTopicDto(1L, "")

        val result = topicService.list(null, pageable)

        assertThat(result).hasSize(1)

        verify(exactly = 0) { topicRepositoryMock.findByCourseName(any(), any()) }
    }

    @Test
    fun listWithCourseShouldReturnCourseTopics() {
        val courseName = "course name"
        val pageable = mockk<Pageable>()
        val page = PageImpl(listOf(Topic()))

        every { topicRepositoryMock.findByCourseName(courseName, pageable) } returns page
        every { topicMapperMock.mapListTopicDtoFrom(any()) } returns ListTopicDto(1L, "")

        val result = topicService.list(courseName, pageable)

        assertThat(result).hasSize(1)

        verify(exactly = 0) { topicRepositoryMock.findAll(any<Pageable>()) }
    }

    @Test
    fun readWithValueShouldReturnResponse() {
        val id = 1L
        val topicRead = mockk<Topic>()
        val topicResult = mockk<ReadTopicDto>()

        every { topicRepositoryMock.findById(id) } returns Optional.of(topicRead)
        every { topicMapperMock.mapReadTopicDtoFrom(topicRead) } returns topicResult

        val result = topicService.read(id)

        assertThat(result).isEqualTo(topicResult)
    }

    @Test
    fun readWithInvalidIdShouldThrowException() {
        val id = 1L

        every { topicRepositoryMock.findById(id) } returns Optional.empty()

        val exception = assertThrows<NotFoundException> { topicService.read(id) }

        assertThat(exception.message).isEqualTo("Topic not found")
    }

    @Test
    fun deleteWithValueShouldReturnResponse() {
        val id = 1L

        every { topicRepositoryMock.deleteById(id) } returns Unit

        assertDoesNotThrow { topicService.delete(id) }
    }
}