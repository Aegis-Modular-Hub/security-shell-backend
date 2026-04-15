package com.aegis_modular_hub

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AegisHubApplication

fun main(args: Array<String>) {
    runApplication<AegisHubApplication>(*args)
    println("✅ ¡Aplicación iniciada correctamente!")
}
