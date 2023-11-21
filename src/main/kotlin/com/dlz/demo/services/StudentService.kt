package com.dlz.demo.services

import java.util.Optional

interface StudentService<E, in ID> {

    fun getAll(): List<E>

    fun getById(id: ID): E?

    fun save(e: E): E

    fun update(id: ID, e: E): E

    fun delete(id: ID): Boolean
}