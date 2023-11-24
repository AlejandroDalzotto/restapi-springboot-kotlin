package com.dlz.demo.payloads

data class ApiResponse(
    val message: String?,
    val success: Boolean = false,
    val reasons: Map<String, String?>? = null,
    val status: Int
)
