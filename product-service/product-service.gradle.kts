description = "상품 서비스"

plugins {
	id("application")
	kotlin("plugin.jpa")
}

dependencies {

	implementation("org.springframework.kafka:spring-kafka:2.9.1")
	implementation("org.springframework.cloud:spring-cloud-starter-config")
	implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
	runtimeOnly("com.h2database:h2")
}
