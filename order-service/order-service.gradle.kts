description = "주문 서비스"

plugins {
    kotlin("plugin.jpa")
}

dependencies {
    implementation("mysql:mysql-connector-java:8.0.30")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.kafka:spring-kafka:2.9.1")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    runtimeOnly("com.h2database:h2")
}
