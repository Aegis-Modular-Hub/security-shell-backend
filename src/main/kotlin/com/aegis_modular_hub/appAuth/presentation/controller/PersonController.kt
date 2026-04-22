package com.aegis_modular_hub.appAuth.presentation.controller

import com.aegis_modular_hub.appAuth.domain.service.Interfaces.PersonService
import com.aegis_modular_hub.appAuth.presentation.request.dto.PersonDto
import com.aegis_modular_hub.appAuth.presentation.response.pojo.PersonPojo
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

@Tag(name = "Auth Service - Persons", description = "Person administration endpoints")
@RestController
@RequestMapping("/api/v1/auth/persons")
class PersonController(
    private val personService: PersonService
) {

    @Operation(summary = "List all persons")
    @GetMapping
    fun getAll(): ResponseEntity<List<PersonPojo>> =
        ResponseEntity.ok(personService.getAll())

    @Operation(summary = "Get a person by id")
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<PersonPojo> =
        ResponseEntity.ok(personService.getById(id))

    @Operation(summary = "Create a person")
    @PostMapping
    fun create(@Valid @RequestBody dto: PersonDto): ResponseEntity<PersonPojo> =
        ResponseEntity.status(HttpStatus.CREATED).body(personService.create(dto))

    @Operation(summary = "Update a person")
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody dto: PersonDto): ResponseEntity<PersonPojo> =
        ResponseEntity.ok(personService.update(id, dto))

    @Operation(summary = "Delete a person")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        personService.delete(id)
        return ResponseEntity.noContent().build()
    }
}
