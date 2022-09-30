description = "사용자 서비스"

plugins {
    kotlin("plugin.jpa")
}


dependencies {
    implementation(project(":util"))

    implementation("mysql:mysql-connector-java:8.0.30")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    runtimeOnly("com.h2database:h2")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("io.jsonwebtoken:jjwt:0.9.1")
    implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j")
}
