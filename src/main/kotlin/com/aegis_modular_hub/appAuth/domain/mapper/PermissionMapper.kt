package com.aegis_modular_hub.appAuth.domain.mapper

import com.aegis_modular_hub.appAuth.domain.entity.Permission
import com.aegis_modular_hub.appAuth.presentation.request.dto.PermissionDto
import com.aegis_modular_hub.appAuth.presentation.response.pojo.PermissionPojo
import org.springframework.stereotype.Component

@Component
class PermissionMapper {
    fun toPojo(permission: Permission): PermissionPojo =
        PermissionPojo(
            id = permission.id,
            name = permission.name,
            description = permission.description,
            httpMethod = permission.httpMethod,
            endpointPattern = permission.endpointPattern
        ).apply {
            createdAt = permission.createdAt
            updatedAt = permission.updatedAt
            createdBy = permission.createdBy
            updatedBy = permission.updatedBy
        }

    fun toEntity(request: PermissionDto): Permission =
        Permission(
            name = request.name,
            description = request.description,
            httpMethod = request.httpMethod.uppercase(),
            endpointPattern = request.endpointPattern
        )

    fun updateEntity(entity: Permission, dto: PermissionDto) {
        entity.name = dto.name
        entity.description = dto.description
        entity.httpMethod = dto.httpMethod.uppercase()
        entity.endpointPattern = dto.endpointPattern
    }
}
