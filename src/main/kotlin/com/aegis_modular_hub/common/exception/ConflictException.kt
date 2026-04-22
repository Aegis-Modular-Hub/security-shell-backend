package com.aegis_modular_hub.common.exception

import org.springframework.http.HttpStatus

class ConflictException(message: String) : ApiException(HttpStatus.CONFLICT, message)
