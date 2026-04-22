package com.aegis_modular_hub.appAuth.domain.mapper

import com.aegis_modular_hub.appAuth.domain.entity.Person
import com.aegis_modular_hub.appAuth.domain.entity.UserAccount
import com.aegis_modular_hub.appAuth.presentation.request.dto.UserDto
import com.aegis_modular_hub.appAuth.presentation.response.pojo.UserPojo
import org.springframework.stereotype.Component

@Component
class UserMapper {
    fun toPojo(user: UserAccount): UserPojo =
        UserPojo(
            id = user.id,
            personId = user.person?.id,
            username = user.username,
            enabled = user.enabled,
            accountLocked = user.accountLocked,
            passwordExpired = user.passwordExpired,
            roleIds = user.roles.mapNotNull { it.id }.toSet()
        ).apply {
            createdAt = user.createdAt
            updatedAt = user.updatedAt
            createdBy = user.createdBy
            updatedBy = user.updatedBy
        }

    fun toEntity(dto: UserDto, person: Person?): UserAccount =
        UserAccount(
            person = person,
            username = dto.username,
            password = dto.password.toString(),
            enabled = dto.enabled,
            accountLocked = dto.accountLocked,
            passwordExpired = dto.passwordExpired
        )

    fun updateEntity(entity: UserAccount, dto: UserDto, person: Person?) {
        entity.person = person
        entity.username = dto.username
        entity.enabled = dto.enabled
        entity.accountLocked = dto.accountLocked
        entity.passwordExpired = dto.passwordExpired
    }
}
