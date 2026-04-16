package com.aegis_modular_hub.appAuth.domain.mapper

import com.aegis_modular_hub.appAuth.domain.entity.Rol
import com.aegis_modular_hub.appAuth.presentation.request.dto.RolDto
import com.aegis_modular_hub.appAuth.presentation.response.pojo.RolPojo
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface RoleMapper {
    fun toPojo(rol: Rol): RolPojo

    @Mapping(target = "id", ignore = true)
    fun toEntity(request: RolDto): Rol
}