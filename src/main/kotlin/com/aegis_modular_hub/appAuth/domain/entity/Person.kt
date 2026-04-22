package com.aegis_modular_hub.appAuth.domain.entity

import com.aegis_modular_hub.common.BaseAuditEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Column
import jakarta.persistence.Id
import jakarta.persistence.Table

import org.hibernate.annotations.SoftDelete
import org.hibernate.annotations.SoftDeleteType
import java.time.LocalDate

@Entity
@Table(name = "persons", schema = "auth")
@SoftDelete(columnName = "active", strategy = SoftDeleteType.ACTIVE)
class Person(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "first_name", nullable = false, length = 100)
    var firstName: String,

    @Column(name = "last_name", nullable = false, length = 100)
    var lastName: String,

    @Column(unique = true, nullable = false, length = 20)
    var identification: String,

    @Column(unique = true, nullable = false, length = 150)
    var email: String,

    @Column(length = 20)
    var phone: String? = null,

    @Column(name = "birth_date")
    var birthDate: LocalDate? = null
) : BaseAuditEntity()
