package com.dlz.demo.exceptions

import com.dlz.demo.payloads.ApiResponse
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.function.Consumer

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException::class)
    fun resourceNotFoundHandler(ex: ResourceNotFoundException): ResponseEntity<ApiResponse> {
        return ResponseEntity<ApiResponse>(
            ApiResponse(
                message = ex.message ?: "Resource not found",
                success = false,
                status = HttpStatus.NOT_FOUND.value()
            ),
            HttpStatus.NOT_FOUND
        )
    }

    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleConstraintViolationException(ex: ConstraintViolationException): ResponseEntity<ApiResponse> {
        return ResponseEntity<ApiResponse>(
            ApiResponse(
                message = ex.message ?: "Incorrect or missing fields",
                success = false,
                status = HttpStatus.BAD_REQUEST.value()
            ),
            HttpStatus.BAD_REQUEST
        )
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<ApiResponse> {
        val errors: MutableMap<String, String?> = HashMap()
        ex.bindingResult.allErrors.forEach(Consumer { error: ObjectError ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.getDefaultMessage()
            errors[fieldName] = errorMessage
        })
        return ResponseEntity<ApiResponse>(
            ApiResponse(
                message = "Bad request",
                success = false,
                status = HttpStatus.BAD_REQUEST.value(),
                reasons = errors
            ),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleHttpMessageNotReadableException(ex: HttpMessageNotReadableException): ResponseEntity<ApiResponse> {
        val errorMessage = "Error de deserialización: ${ex.message}"
        return ResponseEntity(
            ApiResponse(
                message = errorMessage,
                success = false,
                status = HttpStatus.BAD_REQUEST.value()
            ),
            HttpStatus.BAD_REQUEST
        )
    }
}