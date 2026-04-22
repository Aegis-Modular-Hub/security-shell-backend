package com.aegis_modular_hub.appAuth.presentation.request.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class RolDto(
    @field:NotBlank(message = "{rolDto.name.notBlank}")
    @field:Size(max = 50, message = "{rolDto.name.size}")
    val name: String,

    @field:Size(max = 255, message = "{rolDto.description.size}")
    val description: String?,

    val permissionIds: Set<Long> = emptySet()
)
