package com.aegis_modular_hub.appAuth.domain.entity

import com.aegis_modular_hub.common.BaseAuditEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.GenerationType
import java.util.UUID

@Entity
@Table(name = "roles", schema = "auth")
class Rol(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @Column(unique = true, nullable = false, length = 250)
    var name: String,

    @Column(columnDefinition = "TEXT")
    var description: String? = null
) : BaseAuditEntity()