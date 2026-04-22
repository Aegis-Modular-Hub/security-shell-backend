package com.aegis_modular_hub.appAuth.domain.service.Interfaces

import com.aegis_modular_hub.appAuth.presentation.request.dto.PermissionDto
import com.aegis_modular_hub.appAuth.presentation.response.pojo.PermissionPojo

interface PermissionService {
    fun getAll(): List<PermissionPojo>
    fun getById(id: Long): PermissionPojo
    fun create(dto: PermissionDto): PermissionPojo
    fun update(id: Long, dto: PermissionDto): PermissionPojo
    fun delete(id: Long)
}
