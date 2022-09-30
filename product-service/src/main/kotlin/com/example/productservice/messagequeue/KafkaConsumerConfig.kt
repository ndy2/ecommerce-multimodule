package com.example.productservice.messagequeue

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory

@Configuration
class KafkaConsumerConfig(
    kafkaProperties: KafkaProperties
) {
    private val consumerProperties = kafkaProperties.consumer

    @Bean
    fun consumerFactory(): ConsumerFactory<String, String> {
        val properties = mapOf<String, Any>(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to consumerProperties.bootstrapServers,
                ConsumerConfig.GROUP_ID_CONFIG to consumerProperties.groupId,
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to consumerProperties.keyDeserializer,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to consumerProperties.valueDeserializer
        )

        return DefaultKafkaConsumerFactory(properties)
    }

    @Bean
    fun concurrentKafkaListenerContainerFactory(kafkaProperties: KafkaProperties): ConcurrentKafkaListenerContainerFactory<String, String> {
        val kafkaListenerContainerFactory =  ConcurrentKafkaListenerContainerFactory<String, String>()
        kafkaListenerContainerFactory.consumerFactory = consumerFactory()

        return kafkaListenerContainerFactory
    }

}