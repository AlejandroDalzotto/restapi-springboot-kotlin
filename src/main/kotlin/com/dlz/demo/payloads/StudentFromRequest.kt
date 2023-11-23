package com.dlz.demo.payloads

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.PastOrPresent
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

data class StudentFromRequest(
    @NotBlank
    val name: String,

    @NotBlank
    val lastname: String,

    @NotBlank
    @Email
    val email: String,

    @NotBlank
    val code: Long,

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    val birthdate: LocalDate,
)
