import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.4"  apply false
    id("io.spring.dependency-management") version "1.0.14.RELEASE"
    kotlin("plugin.spring") version "1.6.21"  apply false
    kotlin("plugin.jpa") version "1.4.10" apply false
    kotlin("jvm") version "1.6.21"
}

java.sourceCompatibility = JavaVersion.VERSION_17


allprojects {
    group = "com.example"
    version = "0.0.1-SNAPSHOT"
}

subprojects {

    repositories {
        mavenCentral()
    }

    apply(plugin = "java")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-spring")

    extra["springCloudVersion"] = "2021.0.4"

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

        //logging
        implementation("io.github.microutils:kotlin-logging-jvm:2.0.10")
        implementation("org.slf4j:slf4j-api:1.7.30")
    }

    tasks.test {
        useJUnitPlatform()
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

}
