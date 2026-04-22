package com.aegis_modular_hub.appAuth.data.repository

import com.aegis_modular_hub.appAuth.domain.entity.UserAccount

interface UserRepository : GenericRepository<UserAccount, Long> {
    fun existsByUsername(username: String): Boolean
    fun existsByPersonId(personId: Long): Boolean
}
