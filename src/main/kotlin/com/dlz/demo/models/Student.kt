package com.dlz.demo.models

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

@Entity
@Table(name = "students")
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(length = 16)
    @NotBlank(message = "Name is mandatory")
    @NotNull
    var name: String = "",

    @Column
    @NotBlank(message = "Lastname is mandatory")
    @NotNull
    var lastname: String = "",

    @Column
    @Email
    @NotBlank(message = "Email is mandatory")
    @NotNull
    var email: String = "",

    @Column
    @NotBlank(message = "Code is mandatory")
    @NotNull
    var code: Long = 0,

    @Column(nullable = true)
    @NotBlank(message = "Birthdate is mandatory")
    @JsonFormat(pattern = "dd-MM-yyyy")
    var birthdate: LocalDate? = null,

    @Column
    @NotNull
    var state: Boolean = true,

    @Column
    @NotNull
    @Min(17, message = "The student does not meet the required age")
    var age: Byte = 0
)
