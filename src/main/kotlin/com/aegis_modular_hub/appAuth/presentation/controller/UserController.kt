package com.aegis_modular_hub.appAuth.presentation.controller

import com.aegis_modular_hub.appAuth.domain.service.Interfaces.UserService
import com.aegis_modular_hub.appAuth.presentation.request.dto.UserDto
import com.aegis_modular_hub.appAuth.presentation.response.pojo.UserPojo
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

@Tag(name = "Auth Service - Users", description = "User administration endpoints")
@RestController
@RequestMapping("/api/v1/auth/users")
class UserController(
    private val userService: UserService
) {

    @Operation(summary = "List all users")
    @GetMapping
    fun getAll(): ResponseEntity<List<UserPojo>> =
        ResponseEntity.ok(userService.getAll())

    @Operation(summary = "Get a user by id")
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<UserPojo> =
        ResponseEntity.ok(userService.getById(id))

    @Operation(summary = "Create a user")
    @PostMapping
    fun create(@Valid @RequestBody dto: UserDto): ResponseEntity<UserPojo> =
        ResponseEntity.status(HttpStatus.CREATED).body(userService.create(dto))

    @Operation(summary = "Update a user")
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody dto: UserDto): ResponseEntity<UserPojo> =
        ResponseEntity.ok(userService.update(id, dto))

    @Operation(summary = "Delete a user")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        userService.delete(id)
        return ResponseEntity.noContent().build()
    }
}
