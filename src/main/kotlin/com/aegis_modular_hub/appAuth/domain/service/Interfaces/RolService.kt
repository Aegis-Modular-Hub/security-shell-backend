package com.aegis_modular_hub.appAuth.domain.service.Interfaces

import com.aegis_modular_hub.appAuth.presentation.request.dto.RolDto
import com.aegis_modular_hub.appAuth.presentation.response.pojo.RolPojo

interface RolService {
    fun getAll(): List<RolPojo>
    fun getById(id: Long): RolPojo
    fun create(dto: RolDto): RolPojo
    fun update(id: Long, dto: RolDto): RolPojo
    fun delete(id: Long)
}