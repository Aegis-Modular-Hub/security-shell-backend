package com.aegis_modular_hub.appAuth.domain.service.Implements

import com.aegis_modular_hub.appAuth.data.repository.PersonRepository
import com.aegis_modular_hub.appAuth.data.repository.RolRepository
import com.aegis_modular_hub.appAuth.data.repository.UserRepository
import com.aegis_modular_hub.appAuth.domain.entity.Rol
import com.aegis_modular_hub.appAuth.domain.entity.UserAccount
import com.aegis_modular_hub.appAuth.domain.mapper.UserMapper
import com.aegis_modular_hub.appAuth.domain.service.Interfaces.UserService
import com.aegis_modular_hub.appAuth.presentation.request.dto.UserDto
import com.aegis_modular_hub.appAuth.presentation.response.pojo.UserPojo
import com.aegis_modular_hub.common.Messages
import com.aegis_modular_hub.common.exception.ConflictException
import com.aegis_modular_hub.common.exception.ResourceNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import org.springframework.security.crypto.password.PasswordEncoder

@Service
@Transactional
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val personRepository: PersonRepository,
    private val rolRepository: RolRepository,
    private val userMapper: UserMapper,
    private val passwordEncoder: PasswordEncoder
) : UserService {

    override fun getAll(): List<UserPojo> =
        userRepository.findAll().map(userMapper::toPojo)

    override fun getById(id: Long): UserPojo =
        userMapper.toPojo(findUserById(id))

    override fun create(dto: UserDto): UserPojo {
        val username = dto.username.trim().lowercase()

        if (userRepository.existsByUsername(username)) {
            throw ConflictException(Messages.get("error.user.username_exists", username))
        }

        val person = dto.personId?.let { id ->
            val found = personRepository.findById(id)
                .orElseThrow {
                    ResourceNotFoundException(Messages.get("error.person.not_found", id))
                }

            if (userRepository.existsByPersonId(id)) {
                throw ConflictException(Messages.get("error.user.person_already_has_user"))
            }

            found
        }

        val roles = resolveRoles(dto.roleIds)

        val entity = userMapper.toEntity(dto, person).apply {
            this.username = username
            this.password = passwordEncoder.encode(dto.password)
            this.roles = roles.toMutableSet()
        }

        return userMapper.toPojo(userRepository.save(entity))
    }

    override fun update(id: Long, dto: UserDto): UserPojo {
        val existing = findUserById(id)
        val username = dto.username.trim().lowercase()

        if (existing.username != username && userRepository.existsByUsername(username)) {
            throw ConflictException(Messages.get("error.user.username_exists", username))
        }

        val person = dto.personId?.let { id ->
            val found = personRepository.findById(id)
                .orElseThrow {
                    ResourceNotFoundException(Messages.get("error.person.not_found", id))
                }

            if (existing.person?.id != id && userRepository.existsByPersonId(id)) {
                throw ConflictException(Messages.get("error.user.person_already_has_user"))
            }

            found
        }

        userMapper.updateEntity(existing, dto, person)
        existing.username = username

        if (!dto.password.isNullOrBlank()) {
            existing.password = passwordEncoder.encode(dto.password)
        }

        if (dto.roleIds.isNotEmpty()) {
            existing.roles = resolveRoles(dto.roleIds).toMutableSet()
        }

        return userMapper.toPojo(userRepository.save(existing))
    }

    override fun delete(id: Long) {
        if (!userRepository.existsById(id)) {
            throw ResourceNotFoundException(
                Messages.get("error.delete.not_found", Messages.get("user.name"), id)
            )
        }
        userRepository.deleteById(id)
    }

    private fun findUserById(id: Long) =
        userRepository.findById(id)
            .orElseThrow {
                ResourceNotFoundException(Messages.get("error.user.not_found", id))
            }

    private fun resolveRoles(roleIds: Set<Long>): Set<Rol> {
        if (roleIds.isEmpty()) return emptySet()

        val roles = rolRepository.findAllById(roleIds).toSet()
        val foundIds = roles.mapNotNull { it.id }.toSet()
        val missingIds = roleIds - foundIds

        if (missingIds.isNotEmpty()) {
            throw ResourceNotFoundException(Messages.get("error.user.roles_not_found", missingIds))
        }

        return roles
    }
}