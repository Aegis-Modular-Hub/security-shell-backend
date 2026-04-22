package com.aegis_modular_hub.appAuth.domain.entity

import com.aegis_modular_hub.common.BaseAuditEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinTable
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import jakarta.persistence.GenerationType
import org.hibernate.annotations.SoftDelete
import org.hibernate.annotations.SoftDeleteType

@Entity
@Table(name = "roles", schema = "auth")

@SoftDelete(columnName = "active", strategy = SoftDeleteType.ACTIVE)
class Rol(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(unique = true, nullable = false, length = 50)
    var name: String,

    @Column(length = 255)
    var description: String? = null
) : BaseAuditEntity() {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "role_permissions",
        schema = "auth",
        joinColumns = [JoinColumn(name = "role_id")],
        inverseJoinColumns = [JoinColumn(name = "permission_id")]
    )
    var permissions: MutableSet<Permission> = mutableSetOf()

}