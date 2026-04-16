package com.aegis_modular_hub.common

import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun authApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("1-Authentication-Module")
            .pathsToMatch("/api/v1/auth/**")
            .build()
    }

    @Bean
    fun inventoryApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("2-Inventory-Module")
            .pathsToMatch("/api/v1/inventory/**")
            .build()
    }

    @Bean
    fun securityFilterChain(http: org.springframework.security.config.annotation.web.builders.HttpSecurity): org.springframework.security.web.SecurityFilterChain {
        http
            .csrf { it.disable() }
            .authorizeHttpRequests { auth ->
                auth.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                auth.anyRequest().permitAll()
            }
        return http.build()
    }
}