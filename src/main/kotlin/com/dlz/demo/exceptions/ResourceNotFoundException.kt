package com.dlz.demo.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND)
data class ResourceNotFoundException(val mensaje: String?) : RuntimeException(mensaje) {
    companion object {
        private const val serialVersionUID = 1L
    }
}