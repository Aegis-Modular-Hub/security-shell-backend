package com.aegis_modular_hub.appAuth.domain.service.Implements

import com.aegis_modular_hub.appAuth.data.repository.PermissionRepository
import com.aegis_modular_hub.appAuth.data.repository.RolRepository
import com.aegis_modular_hub.appAuth.domain.entity.Permission
import com.aegis_modular_hub.appAuth.domain.mapper.RoleMapper
import com.aegis_modular_hub.appAuth.domain.service.Interfaces.RolService
import com.aegis_modular_hub.appAuth.presentation.request.dto.RolDto
import com.aegis_modular_hub.appAuth.presentation.response.pojo.RolPojo
import com.aegis_modular_hub.common.Messages
import com.aegis_modular_hub.common.exception.ConflictException
import com.aegis_modular_hub.common.exception.ResourceNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class RolServiceImpl(
    private val rolRepository: RolRepository,
    private val permissionRepository: PermissionRepository,
    private val rolMapper: RoleMapper
) : RolService {

    override fun getAll(): List<RolPojo> =
        rolRepository.findAll().map(rolMapper::toPojo)

    override fun getById(id: Long): RolPojo =
        rolMapper.toPojo(findRoleById(id))

    override fun create(dto: RolDto): RolPojo {
        val name = dto.name.trim().uppercase()

        if (rolRepository.existsByName(name)) {
            throw ConflictException(Messages.get("error.role.name_exists", name))
        }

        val permissions = resolvePermissions(dto.permissionIds)

        val entity = rolMapper.toEntity(dto).apply {
            this.name = name
            this.permissions = permissions.toMutableSet()
        }

        return rolMapper.toPojo(rolRepository.save(entity))
    }

    override fun update(id: Long, dto: RolDto): RolPojo {
        val existing = findRoleById(id)
        val name = dto.name.trim().uppercase()

        if (existing.name != name && rolRepository.existsByName(name)) {
            throw ConflictException(Messages.get("error.role.name_exists", name))
        }

        rolMapper.updateEntity(existing, dto)
        existing.name = name

        if (dto.permissionIds.isNotEmpty()) {
            existing.permissions = resolvePermissions(dto.permissionIds).toMutableSet()
        }

        return rolMapper.toPojo(rolRepository.save(existing))
    }

    override fun delete(id: Long) {
        if (rolRepository.isRoleAssignedToUsers(id)) {
            throw ConflictException(Messages.get("error.role.assigned"))
        }

        if (!rolRepository.existsById(id)) {
            throw ResourceNotFoundException(
                Messages.get("error.delete.not_found", Messages.get("role.name"), id)
            )
        }

        rolRepository.deleteById(id)
    }

    private fun findRoleById(id: Long) =
        rolRepository.findById(id)
            .orElseThrow {
                ResourceNotFoundException(Messages.get("error.role.not_found", id))
            }

    private fun resolvePermissions(permissionIds: Set<Long>): Set<Permission> {
        if (permissionIds.isEmpty()) return emptySet()

        val permissions = permissionRepository.findAllById(permissionIds).toSet()
        val foundIds = permissions.mapNotNull { it.id }.toSet()
        val missingIds = permissionIds - foundIds

        if (missingIds.isNotEmpty()) {
            throw ResourceNotFoundException(Messages.get("error.role.permissions_not_found", missingIds))
        }

        return permissions
    }
}
