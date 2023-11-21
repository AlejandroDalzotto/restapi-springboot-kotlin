package com.dlz.demo.services

import com.dlz.demo.models.Student
import com.dlz.demo.repositories.StudentRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StudentServiceImpl : StudentService<Student, Long> {

    @Autowired
    private lateinit var rep: StudentRepository

    override fun getAll(): List<Student> {
        return rep.findAll().filter { it.state ?: true }
    }

    override fun getById(id: Long): Student? {
        return rep.findById(id)
            .orElse(null)
    }

    override fun save(e: Student): Student {
        return rep.save(e)
    }

    override fun update(id: Long, e: Student): Student {
        val student = rep.findById(id).orElseThrow { EntityNotFoundException("Student not found with id: $id")}

        student.name = e.name
        student.lastname = e.lastname
        student.code = e.code
        student.birthdate = e.birthdate

        return student
    }

    override fun delete(id: Long): Boolean {
        val student = rep.getReferenceById(id)
        student.state = false
        return true
    }
}