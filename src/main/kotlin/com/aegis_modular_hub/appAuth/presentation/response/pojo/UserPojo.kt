package com.aegis_modular_hub.appAuth.presentation.response.pojo

import com.aegis_modular_hub.common.pojo.BaseAuditPojo

data class UserPojo(
    val id: Long?,
    val personId: Long?,
    val username: String,
    val enabled: Boolean,
    val accountLocked: Boolean,
    val passwordExpired: Boolean,
    val roleIds: Set<Long>
) : BaseAuditPojo()
