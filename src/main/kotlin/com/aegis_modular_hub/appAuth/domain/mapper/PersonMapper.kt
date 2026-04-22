package com.aegis_modular_hub.appAuth.domain.mapper

import com.aegis_modular_hub.appAuth.domain.entity.Person
import com.aegis_modular_hub.appAuth.presentation.request.dto.PersonDto
import com.aegis_modular_hub.appAuth.presentation.response.pojo.PersonPojo
import org.springframework.stereotype.Component

@Component
class PersonMapper {
    fun toPojo(person: Person): PersonPojo =
        PersonPojo(
            id = person.id,
            firstName = person.firstName,
            lastName = person.lastName,
            identification = person.identification,
            email = person.email,
            phone = person.phone,
            birthDate = person.birthDate
        ).apply {
            createdAt = person.createdAt
            updatedAt = person.updatedAt
            createdBy = person.createdBy
            updatedBy = person.updatedBy
        }

    fun toEntity(request: PersonDto): Person =
        Person(
            firstName = request.firstName,
            lastName = request.lastName,
            identification = request.identification,
            email = request.email,
            phone = request.phone,
            birthDate = request.birthDate
        )

    fun updateEntity(entity: Person, dto: PersonDto) {
        entity.firstName = dto.firstName
        entity.lastName = dto.lastName
        entity.identification = dto.identification
        entity.email = dto.email
        entity.phone = dto.phone
        entity.birthDate = dto.birthDate
    }
}
