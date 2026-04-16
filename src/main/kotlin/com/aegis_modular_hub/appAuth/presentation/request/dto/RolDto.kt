package com.aegis_modular_hub.appAuth.presentation.request.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class RolDto(
    @field:NotBlank(message = "El nombre es obligatorio")
    @field:Size(max = 255)
    val name: String,
    val description: String?
)