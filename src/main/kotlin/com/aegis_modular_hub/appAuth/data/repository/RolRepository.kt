package com.aegis_modular_hub.appAuth.data.repository

import com.aegis_modular_hub.appAuth.domain.entity.Rol
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface RolRepository : GenericRepository<Rol, Long> {
    fun existsByName(name: String): Boolean
    @Query("""
    SELECT COUNT(u) > 0
    FROM UserAccount u JOIN u.roles r
    WHERE r.id = :roleId
""")
    fun isRoleAssignedToUsers(@Param("roleId") roleId: Long): Boolean
}
