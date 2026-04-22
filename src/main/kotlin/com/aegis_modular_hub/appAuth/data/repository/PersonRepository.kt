package com.aegis_modular_hub.appAuth.data.repository

import com.aegis_modular_hub.appAuth.domain.entity.Person
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PersonRepository : GenericRepository<Person, Long> {
    fun existsByEmail(email: String): Boolean
    fun existsByIdentification(identification: String): Boolean
    @Query("""
    SELECT COUNT(u) > 0
    FROM UserAccount u
    WHERE u.person.id = :personId
""")
    fun isPersonAssignedToUser(@Param("personId") personId: Long): Boolean
}
