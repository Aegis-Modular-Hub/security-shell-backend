plugins {
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
    kotlin("plugin.jpa") version "1.9.24"
    kotlin("kapt") version "1.9.24"
}

group = "com.aegis_modular_hub"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // 🟢 Spring Boot Starters (Gestionados por el plugin, sin versión)
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // 🔵 Soporte Kotlin & JSON
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // 🐘 Base de Datos
    runtimeOnly("org.postgresql:postgresql")

    // 🔒 Seguridad JWT (Externas: Sí requieren versión)
    val jjwtVersion = "0.12.5"
    implementation("io.jsonwebtoken:jjwt-api:$jjwtVersion")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:$jjwtVersion")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:$jjwtVersion")

    // 📖 Swagger / OpenAPI
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

    // 🔄 MapStruct (Mapeo)
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    kapt("org.mapstruct:mapstruct-processor:1.5.5.Final")

    // 📊 Reportes
    implementation("net.sf.jasperreports:jasperreports:6.21.2")

    // 🧪 Testeo
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}
