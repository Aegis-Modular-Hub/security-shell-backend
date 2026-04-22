package com.aegis_modular_hub.appAuth.presentation.request.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class RolDto(
    @field:NotBlank(message = "Name is required")
    @field:Size(max = 50)
    val name: String,

    @field:Size(max = 255)
    val description: String?,

    val permissionIds: Set<Long> = emptySet()
)