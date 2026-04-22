package com.aegis_modular_hub.appAuth.presentation.request.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class UserDto(
    val personId: Long?,

    @field:NotBlank
    val username: String,

    val password: String? = null,

    val enabled: Boolean = true,
    val accountLocked: Boolean = false,
    val passwordExpired: Boolean = false,

    val roleIds: Set<Long> = emptySet()
)
