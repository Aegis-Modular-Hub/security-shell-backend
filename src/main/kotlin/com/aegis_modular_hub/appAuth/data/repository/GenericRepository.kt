package com.aegis_modular_hub.appAuth.data.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface GenericRepository<T, ID> : JpaRepository<T, ID>, JpaSpecificationExecutor<T>