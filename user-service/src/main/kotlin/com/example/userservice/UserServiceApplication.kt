package com.example.userservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.ApplicationListener
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.stereotype.Component

@EnableFeignClients
@EnableEurekaClient
@EnableWebSecurity
@SpringBootApplication
class UserServiceApplication

fun main(args: Array<String>) {
    runApplication<UserServiceApplication>(*args)
}

@Component
class PortNumberListener : ApplicationListener<ServletWebServerInitializedEvent> {

    var port: Int = 0
    override fun onApplicationEvent(event: ServletWebServerInitializedEvent) {
        port = event.webServer.port
    }
}
