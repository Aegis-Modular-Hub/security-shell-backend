package com.aegis_modular_hub.appAuth.domain.entity

import com.aegis_modular_hub.common.BaseAuditEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.hibernate.annotations.SoftDelete
import org.hibernate.annotations.SoftDeleteType

@Entity
@Table(name = "users", schema = "auth")

@SoftDelete(columnName = "active", strategy = SoftDeleteType.ACTIVE)
class UserAccount(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "person_id", nullable = true, unique = true)
    var person: Person? = null,

    @Column(unique = true, nullable = false, length = 50)
    var username: String,

    @Column(nullable = false, length = 255)
    var password: String,

    @Column(nullable = false)
    var enabled: Boolean = true,

    @Column(name = "account_locked", nullable = false)
    var accountLocked: Boolean = false,

    @Column(name = "password_expired", nullable = false)
    var passwordExpired: Boolean = false
) : BaseAuditEntity() {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_roles",
        schema = "auth",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    var roles: MutableSet<Rol> = mutableSetOf()
}
