package com.aegis_modular_hub.appAuth.domain.service.Interfaces

import com.aegis_modular_hub.appAuth.presentation.request.dto.PersonDto
import com.aegis_modular_hub.appAuth.presentation.response.pojo.PersonPojo

interface PersonService {
    fun getAll(): List<PersonPojo>
    fun getById(id: Long): PersonPojo
    fun create(dto: PersonDto): PersonPojo
    fun update(id: Long, dto: PersonDto): PersonPojo
    fun delete(id: Long)
}
