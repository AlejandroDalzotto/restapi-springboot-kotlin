package com.dlz.demo.models

import com.dlz.demo.payloads.StudentDto
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "students")
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column
    var name: String = "",

    @Column
    var lastname: String = "",

    @Column
    var email: String = "",

    @Column
    var code: Long = 0,

    @Column(nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    var birthdate: LocalDate,

    @Column
    var state: Boolean = true,

    @Column
    var age: Byte = 0
)

// Utilities

fun List<Student>.toListOfStudentDto(): List<StudentDto> {
    return this.map {
        StudentDto(
            name = it.name,
            lastname = it.lastname,
            email = it.email,
            code = it.code,
            birthdate = it.birthdate
        )
    }
}

fun Student.toStudentDto(): StudentDto {
    return StudentDto(
        name = this.name,
        lastname = this.lastname,
        email = this.email,
        code = this.code,
        birthdate = this.birthdate
    )
}

