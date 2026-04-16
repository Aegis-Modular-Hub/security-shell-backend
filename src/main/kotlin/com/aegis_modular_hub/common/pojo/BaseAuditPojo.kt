package com.aegis_modular_hub.common.pojo

import java.time.LocalDateTime

open class BaseAuditPojo(
    var createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null,
    var createdBy: String? = null,
    var updatedBy: String? = null
)