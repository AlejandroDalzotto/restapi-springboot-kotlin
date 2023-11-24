package com.dlz.demo.services

import com.dlz.demo.exceptions.ResourceNotFoundException
import com.dlz.demo.models.Student
import com.dlz.demo.models.toListOfStudentDto
import com.dlz.demo.models.toStudentDto
import com.dlz.demo.payloads.StudentDto
import com.dlz.demo.repositories.StudentRepository
import com.dlz.demo.utils.calculateAge
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StudentServiceImpl : StudentService<StudentDto, Long> {

    @Autowired
    private lateinit var rep: StudentRepository

    override fun getAll(): List<StudentDto> {
        return rep.findAll().filter { it.state }.toListOfStudentDto()
    }

    override fun getById(id: Long): StudentDto {
        return rep.findById(id)
            .orElseThrow { ResourceNotFoundException("Could not find student $id") }.toStudentDto()
    }

    override fun save(e: StudentDto): StudentDto {
        val studentAge = calculateAge(e.birthdate)
        return rep.save(
            Student(
                name = e.name,
                lastname = e.lastname,
                email = e.email,
                code = e.code,
                birthdate = e.birthdate,
                age = studentAge
            )
        ).toStudentDto()
    }

    override fun update(id: Long, e: StudentDto): StudentDto {
        val studentAge = calculateAge(e.birthdate)

        return rep.findById(id)
            .map { student ->
                student.name = e.name
                student.lastname = e.lastname
                student.code = e.code
                student.birthdate = e.birthdate
                student.age = studentAge
                rep.save(student)
            }
            .orElseThrow {
                ResourceNotFoundException("Could not find student $id")
            }.toStudentDto()
    }

    override fun delete(id: Long) {
        val student = rep.findById(id)
            .orElseThrow {
                ResourceNotFoundException("Could not find student $id")
            }
        student.state = false
    }
}