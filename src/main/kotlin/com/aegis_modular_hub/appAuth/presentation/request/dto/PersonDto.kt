package com.aegis_modular_hub.appAuth.presentation.request.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class PersonDto(
    @field:NotBlank(message = "{personDto.firstName.notBlank}")
    @field:Size(max = 100, message = "{personDto.firstName.size}")
    val firstName: String,

    @field:NotBlank(message = "{personDto.lastName.notBlank}")
    @field:Size(max = 100, message = "{personDto.lastName.size}")
    val lastName: String,

    @field:NotBlank(message = "{personDto.identification.notBlank}")
    @field:Size(max = 20, message = "{personDto.identification.size}")
    val identification: String,

    @field:NotBlank(message = "{personDto.email.notBlank}")
    @field:Email(message = "{personDto.email.email}")
    @field:Size(max = 150, message = "{personDto.email.size}")
    val email: String,

    @field:Size(max = 20, message = "{personDto.phone.size}")
    val phone: String?,

    val birthDate: LocalDate?
)
