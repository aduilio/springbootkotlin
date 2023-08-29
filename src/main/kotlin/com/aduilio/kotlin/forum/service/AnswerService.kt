package com.aduilio.kotlin.forum.service

import com.aduilio.kotlin.forum.dto.CreateAnswerDto
import com.aduilio.kotlin.forum.dto.ReadAnswerDto
import com.aduilio.kotlin.forum.mapper.AnswerMapper
import com.aduilio.kotlin.forum.repository.AnswerRepository
import org.springframework.stereotype.Service

/**
 * Provides services related to topics.
 */
@Service
class AnswerService(private val answerRepository: AnswerRepository,
                    private val answerMapper: AnswerMapper) {

    /**
     * Creates an answer.
     *
     * @param createAnswerDto the information to be created
     * @return ReadAnswerDto
     */
    fun create(createAnswerDto: CreateAnswerDto) : ReadAnswerDto {
        val answer = answerMapper.mapAnswerFrom(createAnswerDto)
        val result = answerRepository.save(answer)

        return answerMapper.mapReadAnswerDtoFrom(result)
    }
}