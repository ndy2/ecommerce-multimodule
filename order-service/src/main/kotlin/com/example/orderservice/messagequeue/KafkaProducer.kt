package com.example.orderservice.messagequeue

import com.example.orderservice.jpa.OrderEntity
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaProducer(
        private val kafkaTemplate: KafkaTemplate<String, String>
) {

    private val mapper = ObjectMapper()

    fun send(topic: String, orderEntity: OrderEntity) {

        val orderEntityString = mapper.writeValueAsString(orderEntity)
        kafkaTemplate.send(topic, orderEntityString)
        println("Kafka Producer sent data from the Order microservice: $orderEntityString");
    }
}