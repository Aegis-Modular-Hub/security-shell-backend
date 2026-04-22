package com.aegis_modular_hub.appAuth.presentation.response.pojo

import com.aegis_modular_hub.common.pojo.BaseAuditPojo
import java.time.LocalDate

data class PersonPojo(
    val id: Long?,
    val firstName: String,
    val lastName: String,
    val identification: String,
    val email: String,
    val phone: String?,
    val birthDate: LocalDate?
) : BaseAuditPojo()
