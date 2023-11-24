package com.dlz.demo.payloads

import jakarta.validation.constraints.*
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

data class StudentDto(
    @field:NotBlank(message = "Name cannot be blank")
    @field:NotNull(message = "Name must be provided")
    val name: String,

    @field:NotBlank(message = "Lastname cannot be blank")
    @field:NotNull(message = "Lastname must be provided")
    val lastname: String,

    @field:NotBlank(message = "Email cannot be blank")
    @field:Email(message = "The email must have a valid format. Example: mail123@example.com")
    @field:NotNull(message = "Email must be provided")
    val email: String,

    @field:NotNull(message = "Code must be provided")
    val code: Long,

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @field:NotNull(message = "Birthdate must be provided")
    val birthdate: LocalDate
)
