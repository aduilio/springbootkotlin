package com.aduilio.kotlin.forum.exception

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

/**
 * Intercepts the erro messages.
 */
@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMethodArgumentNotValidException(exception: MethodArgumentNotValidException,
                                              request: HttpServletRequest): ErrorMessage {
        val errors = HashMap<String, String?>()
        exception.bindingResult.fieldErrors.forEach { error -> errors[error.field] = error.defaultMessage }

        return ErrorMessage(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name,
                errors.toString(),
                request.servletPath
        )
    }

    data class ErrorMessage(val timestamp: LocalDateTime,
                            val status: Int,
                            val error: String,
                            val message: String,
                            val path: String)
}