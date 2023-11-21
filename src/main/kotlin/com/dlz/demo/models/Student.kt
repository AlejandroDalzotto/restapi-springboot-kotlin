package com.dlz.demo.models

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "students")
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0,
    @Column
    var name: String? = "",
    @Column
    var lastname: String? = "",
    @Column
    var code: Long? = 0,
    @Column
    var birthdate: LocalDateTime? = null,
    @Column
    var state: Boolean? = true
)
