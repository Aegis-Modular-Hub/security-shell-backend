package com.aegis_modular_hub.common.error

import java.time.LocalDateTime

data class ApiError(
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val error: String,
    val message: String,
    val path: String,
    val fieldErrors: List<FieldErrorItem> = emptyList()
)
