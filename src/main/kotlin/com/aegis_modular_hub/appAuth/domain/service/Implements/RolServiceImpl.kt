package com.aegis_modular_hub.appAuth.domain.service.Implements

import com.aegis_modular_hub.appAuth.data.repository.RolRepository
import com.aegis_modular_hub.appAuth.domain.mapper.RoleMapper
import com.aegis_modular_hub.appAuth.domain.service.Interfaces.RolService
import com.aegis_modular_hub.appAuth.presentation.request.dto.RolDto
import com.aegis_modular_hub.appAuth.presentation.response.pojo.RolPojo
import org.springframework.stereotype.Service
import java.util.*

@Service
class RolServiceImpl(
    private val rolRepository: RolRepository,
    private val rolMapper: RoleMapper
) : RolService {

    override fun getAll(): List<RolPojo> {
        return rolRepository.findAll().map { rolMapper.toPojo(it) }
    }

    override fun getById(id: UUID): RolPojo {
        val entity = rolRepository.findById(id)
            .orElseThrow { RuntimeException("Rol no encontrado con ID: $id") }
        return rolMapper.toPojo(entity)
    }

    override fun create(dto: RolDto): RolPojo {
        val entity = rolMapper.toEntity(dto)
        val saved = rolRepository.save(entity)
        return rolMapper.toPojo(saved)
    }

    override fun update(id: UUID, dto: RolDto): RolPojo {
        val existing = rolRepository.findById(id)
            .orElseThrow { RuntimeException("Rol no encontrado") }

        existing.name = dto.name
        existing.description = dto.description

        return rolMapper.toPojo(rolRepository.save(existing))
    }

    override fun delete(id: UUID) {
        rolRepository.deleteById(id)
    }
}