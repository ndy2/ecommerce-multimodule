package com.example.userservice.config

import com.example.userservice.FeignErrorDecoder
import feign.codec.ErrorDecoder
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class WebConfig {

    @LoadBalanced
    @Bean
    fun restTemplate() : RestTemplate {
        return RestTemplate()
    }

    @Bean
    fun errorDecoder() : ErrorDecoder{
        return FeignErrorDecoder()
    }
}