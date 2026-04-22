package com.aegis_modular_hub.appAuth.presentation.controller

import com.aegis_modular_hub.appAuth.domain.service.Interfaces.RolService
import com.aegis_modular_hub.appAuth.presentation.request.dto.RolDto
import com.aegis_modular_hub.appAuth.presentation.response.pojo.RolPojo
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Auth Service - Roles", description = "Módulo para la gestión de roles y permisos")
@RestController
@RequestMapping("/api/v1/auth/roles")
class RolController(
    private val rolService: RolService
) {

    @Operation(summary = "Listar todos los roles")
    @GetMapping
    fun getAll(): ResponseEntity<List<RolPojo>> =
        ResponseEntity.ok(rolService.getAll())

    @Operation(summary = "Obtener un rol por su ID")
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<RolPojo> =
        ResponseEntity.ok(rolService.getById(id))

    @Operation(summary = "Crear un nuevo rol")
    @PostMapping
    fun create(@Valid @RequestBody dto: RolDto): ResponseEntity<RolPojo> =
        ResponseEntity.status(HttpStatus.CREATED).body(rolService.create(dto))

    @Operation(summary = "Actualizar un rol existente")
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody dto: RolDto): ResponseEntity<RolPojo> =
        ResponseEntity.ok(rolService.update(id, dto))

    @Operation(summary = "Eliminar un rol por su ID")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        rolService.delete(id)
        return ResponseEntity.noContent().build()
    }
}