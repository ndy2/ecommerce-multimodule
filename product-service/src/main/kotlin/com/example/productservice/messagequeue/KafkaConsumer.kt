package com.example.productservice.messagequeue

import com.example.productservice.persistence.ProductDao
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import mu.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class KafkaConsumer(
    private val productDao: ProductDao,
) {
    private val mapper = ObjectMapper()

    private val log = KotlinLogging.logger {}

    @Transactional
    @KafkaListener(topics = ["example-catalog-topic"], groupId = "consumerGroupId")
    fun updateQuantity(kafkaMessage: String) {
        println("Kafka Message : -> $kafkaMessage")
        var map: Map<Any, Any> = emptyMap()

        try {
            map = mapper.readValue(
                kafkaMessage,
                object : TypeReference<Map<Any, Any>>() {}
            )
        } catch (e: JsonProcessingException) {
            log.error("json processing exception", e)
        }

        val productId = map["productId"] as String
        val quantity = map["quantity"] as Int

        val productEntity = productDao.findByProductId(productId)
        productEntity.stock -= quantity
    }

}