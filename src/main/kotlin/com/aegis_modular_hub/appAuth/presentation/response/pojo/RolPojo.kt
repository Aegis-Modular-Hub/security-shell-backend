package com.aegis_modular_hub.appAuth.presentation.response.pojo

import com.aegis_modular_hub.common.pojo.BaseAuditPojo

data class RolPojo(
    val id: Long?,
    val name: String,
    val description: String?,
    val permissionIds: Set<Long>
) : BaseAuditPojo()