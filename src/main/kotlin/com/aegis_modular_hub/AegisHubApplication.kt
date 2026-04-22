package com.aegis_modular_hub

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AegisHubApplication

fun main(args: Array<String>) {
    val context = runApplication<AegisHubApplication>(*args)

    val port = context.environment.getProperty("server.port") ?: "8080"
    val host = "http://localhost"

    println("""
        ----------------------------------------------------------
        ✅ ¡Aegis Hub se ha iniciado correctamente!

        📌 Swagger UI:   $host:$port/swagger-ui.html
        ----------------------------------------------------------
    """.trimIndent())
}
