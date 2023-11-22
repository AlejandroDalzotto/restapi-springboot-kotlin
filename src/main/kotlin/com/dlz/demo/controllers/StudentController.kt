package com.dlz.demo.controllers

import com.dlz.demo.models.Student
import com.dlz.demo.services.StudentServiceImpl
import jakarta.annotation.Nullable
import java.util.Optional
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
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
class StudentController {

    @Autowired
    private lateinit var service: StudentServiceImpl

    @GetMapping("/students/")
    fun getAll() = service.getAll()

    @GetMapping("/students/{id}/")
    fun getStudentById(@PathVariable id: Long) = ResponseEntity.ok().body(service.getById(id))

    @PostMapping("/students/")
    fun save(@RequestBody student: Student) = ResponseEntity.status(HttpStatus.CREATED).body(service.save(student))

    @PutMapping("/students/{id}/")
    fun update(@PathVariable id: Long, @RequestBody student: Student) = ResponseEntity.ok().body(service.update(id, student))

    @DeleteMapping("/students/{id}/")
    fun delete(@PathVariable id: Long): ResponseEntity<Student> {
        service.delete(id)
        return ResponseEntity.ok().build()
    }
}