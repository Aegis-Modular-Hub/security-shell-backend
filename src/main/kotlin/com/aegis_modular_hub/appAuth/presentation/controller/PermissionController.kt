package com.aegis_modular_hub.appAuth.presentation.controller

import com.aegis_modular_hub.appAuth.domain.service.Interfaces.PermissionService
import com.aegis_modular_hub.appAuth.presentation.request.dto.PermissionDto
import com.aegis_modular_hub.appAuth.presentation.response.pojo.PermissionPojo
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Auth Service - Permissions", description = "Permission administration endpoints")
@RestController
@RequestMapping("/api/v1/auth/permissions")
class PermissionController(
    private val permissionService: PermissionService
) {

    @Operation(summary = "List all permissions")
    @GetMapping
    fun getAll(): ResponseEntity<List<PermissionPojo>> =
        ResponseEntity.ok(permissionService.getAll())

    @Operation(summary = "Get a permission by id")
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<PermissionPojo> =
        ResponseEntity.ok(permissionService.getById(id))

    @Operation(summary = "Create a permission")
    @PostMapping
    fun create(@Valid @RequestBody dto: PermissionDto): ResponseEntity<PermissionPojo> =
        ResponseEntity.status(HttpStatus.CREATED).body(permissionService.create(dto))

    @Operation(summary = "Update a permission")
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody dto: PermissionDto): ResponseEntity<PermissionPojo> =
        ResponseEntity.ok(permissionService.update(id, dto))

    @Operation(summary = "Delete a permission")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        permissionService.delete(id)
        return ResponseEntity.noContent().build()
    }
}
