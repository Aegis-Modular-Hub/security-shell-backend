package com.aegis_modular_hub.appAuth.presentation.request.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class PermissionDto(
    @field:NotBlank(message = "Name is required")
    @field:Size(max = 100)
    val name: String,

    @field:Size(max = 255)
    val description: String?,

    @field:NotBlank(message = "HTTP method is required")
    @field:Size(max = 10)
    val httpMethod: String,

    @field:NotBlank(message = "Endpoint pattern is required")
    @field:Size(max = 255)
    val endpointPattern: String
)
