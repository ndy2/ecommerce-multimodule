package com.example.orderservice.messagequeue

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class KafkaProducerConfig(
    kafkaProperties: KafkaProperties
) {
    private val producerProperties = kafkaProperties.producer

    @Bean
    fun producerFactory(): ProducerFactory<String, String> {
        val properties = mapOf<String, Any>(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to producerProperties.bootstrapServers,
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to producerProperties.keySerializer,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to producerProperties.valueSerializer,
        )
        return DefaultKafkaProducerFactory(properties)
    }

    @Bean
    fun kafkaTemplate(kafkaProperties: KafkaProperties): KafkaTemplate<String, String> {
        return KafkaTemplate(producerFactory())
    }

}