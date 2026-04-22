package com.aegis_modular_hub.appAuth.domain.service.Interfaces

import com.aegis_modular_hub.appAuth.presentation.request.dto.UserDto
import com.aegis_modular_hub.appAuth.presentation.response.pojo.UserPojo

interface UserService {
    fun getAll(): List<UserPojo>
    fun getById(id: Long): UserPojo
    fun create(dto: UserDto): UserPojo
    fun update(id: Long, dto: UserDto): UserPojo
    fun delete(id: Long)
}
