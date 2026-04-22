package com.aegis_modular_hub.appAuth.domain.entity

import com.aegis_modular_hub.common.BaseAuditEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.SoftDelete
import org.hibernate.annotations.SoftDeleteType

@Entity
@Table(name = "permissions", schema = "auth")
@SoftDelete(columnName = "active", strategy = SoftDeleteType.ACTIVE)
class Permission(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, length = 100)
    var name: String,

    @Column(length = 255)
    var description: String? = null,

    @Column(name = "http_method", nullable = false, length = 10)
    var httpMethod: String,

    @Column(name = "endpoint_pattern", nullable = false, length = 255)
    var endpointPattern: String
) : BaseAuditEntity()
