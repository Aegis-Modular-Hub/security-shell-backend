package com.aegis_modular_hub.appAuth.data.repository

import com.aegis_modular_hub.appAuth.domain.entity.Permission
import org.springframework.data.jpa.repository.Query

interface PermissionRepository : GenericRepository<Permission, Long> {
    fun existsByName(name: String): Boolean
    fun existsByHttpMethodAndEndpointPattern(
        httpMethod: String,
        endpointPattern: String
    ): Boolean

    @Query("""
    SELECT COUNT(r) > 0
    FROM Rol r JOIN r.permissions p
    WHERE p.id = :permissionId
""")
    fun isPermissionAssigned(permissionId: Long): Boolean
}
