package com.example.productservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.sleuth.zipkin2.ZipkinProperties
import org.springframework.kafka.annotation.EnableKafka

@EnableKafka
@EnableEurekaClient
@SpringBootApplication
class ProductServiceApplication

fun main(args: Array<String>) {
	val ac = runApplication<ProductServiceApplication>(*args)

	val zipkinProperties = ac.getBean(ZipkinProperties::class.java)
	println("zipkinProperties${zipkinProperties}")
}

