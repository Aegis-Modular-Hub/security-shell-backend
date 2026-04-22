package com.aegis_modular_hub.appAuth.domain.service.Implements

import com.aegis_modular_hub.appAuth.data.repository.PersonRepository
import com.aegis_modular_hub.appAuth.domain.mapper.PersonMapper
import com.aegis_modular_hub.appAuth.domain.service.Interfaces.PersonService
import com.aegis_modular_hub.appAuth.presentation.request.dto.PersonDto
import com.aegis_modular_hub.appAuth.presentation.response.pojo.PersonPojo
import com.aegis_modular_hub.common.exception.ConflictException
import com.aegis_modular_hub.common.exception.ResourceNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class PersonServiceImpl(
    private val personRepository: PersonRepository,
    private val personMapper: PersonMapper
) : PersonService {

    override fun getAll(): List<PersonPojo> =
        personRepository.findAll().map(personMapper::toPojo)

    override fun getById(id: Long): PersonPojo =
        personMapper.toPojo(findPersonById(id))

    override fun create(dto: PersonDto): PersonPojo {
        validateUniqueness(dto.email, dto.identification)
        val saved = personRepository.save(personMapper.toEntity(dto))
        return personMapper.toPojo(saved)
    }

    override fun update(id: Long, dto: PersonDto): PersonPojo {
        val existing = findPersonById(id)

        if (existing.email != dto.email && personRepository.existsByEmail(dto.email)) {
            throw ConflictException("A person with email '${dto.email}' already exists")
        }

        if (existing.identification != dto.identification &&
            personRepository.existsByIdentification(dto.identification)
        ) {
            throw ConflictException("A person with identification '${dto.identification}' already exists")
        }

        personMapper.updateEntity(existing, dto)

        return personMapper.toPojo(personRepository.save(existing))
    }

    override fun delete(id: Long) {
        if (personRepository.isPersonAssignedToUser(id)) {
            throw ConflictException("Person is assigned to a user and cannot be deleted")
        }
        personRepository.deleteById(id)
    }

    private fun findPersonById(id: Long) =
        personRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Person not found with id: $id") }

    private fun validateUniqueness(email: String, identification: String) {
        if (personRepository.existsByEmail(email)) {
            throw ConflictException("A person with email '$email' already exists")
        }

        if (personRepository.existsByIdentification(identification)) {
            throw ConflictException("A person with identification '$identification' already exists")
        }
    }
}
