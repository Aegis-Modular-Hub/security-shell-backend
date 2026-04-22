package com.aegis_modular_hub.appAuth.domain.service.Implements

import com.aegis_modular_hub.appAuth.data.repository.PermissionRepository
import com.aegis_modular_hub.appAuth.domain.mapper.PermissionMapper
import com.aegis_modular_hub.appAuth.domain.service.Interfaces.PermissionService
import com.aegis_modular_hub.appAuth.presentation.request.dto.PermissionDto
import com.aegis_modular_hub.appAuth.presentation.response.pojo.PermissionPojo
import com.aegis_modular_hub.common.exception.ConflictException
import com.aegis_modular_hub.common.exception.ResourceNotFoundException
import org.springframework.stereotype.Service

@Service
class PermissionServiceImpl(
    private val permissionRepository: PermissionRepository,
    private val permissionMapper: PermissionMapper
) : PermissionService {

    override fun getAll(): List<PermissionPojo> =
        permissionRepository.findAll().map(permissionMapper::toPojo)

    override fun getById(id: Long): PermissionPojo =
        permissionMapper.toPojo(findPermissionById(id))

    override fun create(dto: PermissionDto): PermissionPojo {
        if (permissionRepository.existsByName(dto.name)) {
            throw ConflictException("A permission with name '${dto.name}' already exists")
        }

        val saved = permissionRepository.save(permissionMapper.toEntity(dto))
        return permissionMapper.toPojo(saved)
    }

    override fun update(id: Long, dto: PermissionDto): PermissionPojo {
        val existing = findPermissionById(id)

        if (existing.name != dto.name && permissionRepository.existsByName(dto.name)) {
            throw ConflictException("A permission with name '${dto.name}' already exists")
        }
        permissionMapper.updateEntity(existing, dto)

        return permissionMapper.toPojo(permissionRepository.save(existing))
    }

    override fun delete(id: Long) {
        if (permissionRepository.isPermissionAssigned(id)) {
            throw ConflictException("Permission is assigned to roles and cannot be deleted")
        }
        permissionRepository.deleteById(id)
    }

    private fun findPermissionById(id: Long) =
        permissionRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Permission not found with id: $id") }
}
