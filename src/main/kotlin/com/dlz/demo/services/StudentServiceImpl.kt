package com.dlz.demo.services

import com.dlz.demo.exceptions.ResourceNotFoundException
import com.dlz.demo.models.Student
import com.dlz.demo.repositories.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StudentServiceImpl : StudentService<Student, Long> {

    @Autowired
    private lateinit var rep: StudentRepository

    override fun getAll(): List<Student> {
        return rep.findAll().filter { it.state ?: true }
    }

    override fun getById(id: Long): Student {
        return rep.findById(id)
            .orElseThrow { ResourceNotFoundException("Could not find student $id") }
    }

    override fun save(e: Student): Student {
        return rep.save(e)
    }

    override fun update(id: Long, e: Student): Student {
        return rep.findById(id)
            .map { student ->
                student.name = e.name
                student.lastname = e.lastname
                student.code = e.code
                student.birthdate = e.birthdate
                rep.save(student)
            }
            .orElseThrow {
                ResourceNotFoundException("Could not find student $id")
            }
    }

    override fun delete(id: Long) {
        val student = rep.findById(id)
            .orElseThrow {
                ResourceNotFoundException("Could not find student $id")
            }
        student.state = false
    }
}