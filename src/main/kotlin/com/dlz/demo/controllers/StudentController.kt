package com.dlz.demo.controllers

import com.dlz.demo.models.Student
import com.dlz.demo.payloads.StudentDto
import com.dlz.demo.services.StudentServiceImpl
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
class StudentController @Autowired constructor(
    private val service: StudentServiceImpl,
) {

    @GetMapping("/students/")
    fun getAll() = service.getAll()

    @GetMapping("/students/{id}/")
    fun getStudentById(@PathVariable id: Long) = ResponseEntity.ok().body(service.getById(id))

    @PostMapping("/students/")
    fun save(@Valid @RequestBody s: StudentDto): ResponseEntity<Any> {
        val savedStudent = service.save(s)

        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent)
    }

    @PutMapping("/students/{id}/")
    fun update(@PathVariable id: Long, @Valid @RequestBody student: StudentDto) =
        ResponseEntity.ok().body(service.update(id, student))

    @DeleteMapping("/students/{id}/")
    fun delete(@PathVariable id: Long): ResponseEntity<Student> {
        service.delete(id)
        return ResponseEntity.ok().build()
    }
}