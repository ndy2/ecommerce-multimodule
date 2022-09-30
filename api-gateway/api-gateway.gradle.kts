description = "api-gateway, 인증 필터 제공"

plugins {
}

dependencies {
    implementation("io.jsonwebtoken:jjwt:0.9.1")
    implementation("org.springframework.cloud:spring-cloud-starter-gateway")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

    // m1 macos issue
    implementation("io.netty:netty-resolver-dns-native-macos:4.1.82.Final:osx-aarch_64")

    // require javax/xml/bind/DatatypeConverter to decode jwt
    implementation("javax.xml.bind:jaxb-api:2.4.0-b180830.0359")
    implementation("com.sun.xml.bind:jaxb-core:4.0.1")
    implementation("com.sun.xml.bind:jaxb-impl:4.0.1")
}
