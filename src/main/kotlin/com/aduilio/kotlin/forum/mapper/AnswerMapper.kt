package com.aduilio.kotlin.forum.mapper

import com.aduilio.kotlin.forum.dto.CreateAnswerDto
import com.aduilio.kotlin.forum.dto.ReadAnswerDto
import com.aduilio.kotlin.forum.entity.Answer
import com.aduilio.kotlin.forum.entity.Topic
import com.aduilio.kotlin.forum.entity.User
import org.springframework.stereotype.Component

/**
 * Maps the attributes from/to DTOs.
 */
@Component
class AnswerMapper {

    /**
     * Maps a CreateAnswerDto to Answer.
     *
     * @param createAnswerDto to be mapped
     * @return Answer
     */
    fun mapAnswerFrom(createAnswerDto: CreateAnswerDto): Answer {
        return Answer(
                message = createAnswerDto.message,
                topic = Topic(
                        id = createAnswerDto.topic.id
                ),
                author = User(
                        id = createAnswerDto.author.id
                ),
                solutionAccepted = false
        )
    }

    /**
     * Maps an Answer to ReadAnswerDto.
     *
     * @param answer to be mapped
     * @return ReadAnswerDto
     */
    fun mapReadAnswerDtoFrom(answer: Answer): ReadAnswerDto {
        return ReadAnswerDto(
                id = answer.id!!,
                message = answer.message,
                date = answer.date,
                accepted = answer.solutionAccepted
        )
    }
}