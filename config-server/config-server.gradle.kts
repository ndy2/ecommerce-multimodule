description = "config-server application yml 정보를 제공한다"

plugins {
    id("application")
}

dependencies {
    implementation("org.springframework.cloud:spring-cloud-config-server")
}
