package com.aegis_modular_hub.common.exception

import org.springframework.http.HttpStatus

class ResourceNotFoundException(message: String) : ApiException(HttpStatus.NOT_FOUND, message)
