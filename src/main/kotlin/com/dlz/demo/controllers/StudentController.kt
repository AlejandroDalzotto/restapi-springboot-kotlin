package com.dlz.demo.controllers

import com.dlz.demo.models.Student
import com.dlz.demo.payloads.StudentFromRequest
import com.dlz.demo.services.StudentServiceImpl
import com.dlz.demo.utils.calculateAge
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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

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
    fun save(@RequestBody s: StudentFromRequest): ResponseEntity<Any> {
        try {
            val birthdate = LocalDate.parse(s.birthdate.toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            val studentAge = calculateAge(birthdate).toByte()

            val student = Student(
                name = s.name,
                lastname = s.lastname,
                email = s.email,
                code = s.code,
                state = true,
                birthdate = birthdate,
                age = studentAge
            )

            val savedStudent = service.save(student)

            return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent)
        } catch (ex: DateTimeParseException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Formato de fecha inválido. Debe ser yyyy-MM-dd.")
        } catch (ex: Exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el estudiante.")
        }
    }

    @PutMapping("/students/{id}/")
    fun update(@PathVariable id: Long, @RequestBody student: Student) = ResponseEntity.ok().body(service.update(id, student))

    @DeleteMapping("/students/{id}/")
    fun delete(@PathVariable id: Long): ResponseEntity<Student> {
        service.delete(id)
        return ResponseEntity.ok().build()
    }
}