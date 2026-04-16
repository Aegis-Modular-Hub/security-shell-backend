package com.aegis_modular_hub.appAuth.presentation.response.pojo

import com.aegis_modular_hub.common.pojo.BaseAuditPojo
import java.util.UUID

data class RolPojo(
    val id: UUID?,
    val name: String,
    val description: String?
) : BaseAuditPojo()