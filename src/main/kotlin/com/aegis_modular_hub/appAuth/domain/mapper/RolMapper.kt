package com.aegis_modular_hub.appAuth.domain.mapper

import com.aegis_modular_hub.appAuth.domain.entity.Rol
import com.aegis_modular_hub.appAuth.presentation.request.dto.RolDto
import com.aegis_modular_hub.appAuth.presentation.response.pojo.RolPojo
import org.springframework.stereotype.Component

@Component
class RoleMapper {
    fun toPojo(role: Rol): RolPojo =
        RolPojo(
            id = role.id,
            name = role.name,
            description = role.description,
            permissionIds = role.permissions.mapNotNull { it.id }.toSet()
        ).apply {
            createdAt = role.createdAt
            updatedAt = role.updatedAt
            createdBy = role.createdBy
            updatedBy = role.updatedBy
        }

    fun toEntity(request: RolDto): Rol =
        Rol(
            name = request.name,
            description = request.description
        )

    fun updateEntity(entity: Rol, dto: RolDto) {
        entity.name = dto.name
        entity.description = dto.description
    }
}