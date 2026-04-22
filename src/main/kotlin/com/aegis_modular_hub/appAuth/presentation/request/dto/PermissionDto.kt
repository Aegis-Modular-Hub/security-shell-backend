package com.aegis_modular_hub.appAuth.presentation.request.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class PermissionDto(
    @field:NotBlank(message = "{permission.name.notBlank}")
    @field:Size(max = 100, message = "{permission.name.size}")
    val name: String,

    @field:Size(max = 255, message = "{permission.description.size}")
    val description: String?,

    @field:NotBlank(message = "{permission.httpMethod.notBlank}")
    @field:Size(max = 10, message = "{permission.httpMethod.size}")
    val httpMethod: String,

    @field:NotBlank(message = "{permission.endpointPattern.notBlank}")
    @field:Size(max = 255, message = "{permission.endpointPattern.size}")
    val endpointPattern: String
)
