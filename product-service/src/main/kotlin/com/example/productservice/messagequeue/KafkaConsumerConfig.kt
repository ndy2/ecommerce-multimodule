package com.example.productservice.messagequeue

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory

@Configuration
class KafkaConsumerConfig {

    @Bean
    fun consumerFactory(): ConsumerFactory<String, String> {
        val properties = mapOf<String, Any>(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to "127.0.0.1:9092",
                ConsumerConfig.GROUP_ID_CONFIG to "consumerGroupId",
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
        )
        return DefaultKafkaConsumerFactory(properties)
    }

    @Bean
    fun concurrentKafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, String> {
        val kafkaListenerContainerFactory =  ConcurrentKafkaListenerContainerFactory<String, String>()
        kafkaListenerContainerFactory.consumerFactory = consumerFactory()

        return kafkaListenerContainerFactory
    }

}