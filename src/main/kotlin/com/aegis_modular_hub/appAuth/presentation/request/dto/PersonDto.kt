package com.aegis_modular_hub.appAuth.presentation.request.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class PersonDto(
    @field:NotBlank(message = "First name is required")
    @field:Size(max = 100)
    val firstName: String,

    @field:NotBlank(message = "Last name is required")
    @field:Size(max = 100)
    val lastName: String,

    @field:NotBlank(message = "Identification is required")
    @field:Size(max = 20)
    val identification: String,

    @field:NotBlank(message = "Email is required")
    @field:Email(message = "Email format is invalid")
    @field:Size(max = 150)
    val email: String,

    @field:Size(max = 20)
    val phone: String?,

    val birthDate: LocalDate?
)
