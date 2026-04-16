package com.aegis_modular_hub.util

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.util.*

@Configuration
@EnableJpaAuditing
class AuditConfig {
    @Bean
    fun auditorProvider(): AuditorAware<String> {
        // Por ahora retornamos un nombre fijo. Luego camniar por SecurityContextHolder (Spring Security)
        return AuditorAware { Optional.of("SYSTEM_USER") }
    }
}