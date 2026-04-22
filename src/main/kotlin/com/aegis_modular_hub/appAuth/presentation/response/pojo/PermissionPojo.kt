package com.aegis_modular_hub.appAuth.presentation.response.pojo

import com.aegis_modular_hub.common.pojo.BaseAuditPojo

data class PermissionPojo(
    val id: Long?,
    val name: String,
    val description: String?,
    val httpMethod: String,
    val endpointPattern: String
) : BaseAuditPojo()
