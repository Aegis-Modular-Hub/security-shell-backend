package com.aegis_modular_hub.common.exception

import org.springframework.http.HttpStatus

open class ApiException(
    val status: HttpStatus,
    override val message: String
) : RuntimeException(message)
