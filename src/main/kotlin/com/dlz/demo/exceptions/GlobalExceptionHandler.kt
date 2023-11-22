package com.dlz.demo.exceptions

import com.dlz.demo.payloads.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException::class)
    fun resourceNotFoundHandler(ex: ResourceNotFoundException): ResponseEntity<ApiResponse> {
        return ResponseEntity<ApiResponse>(
            ApiResponse(ex.message ?: "Resource not found", false),
            HttpStatus.NOT_FOUND
        )
    }
}