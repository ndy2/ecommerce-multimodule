package com.example.apigateway.filter

import mu.KotlinLogging
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class GlobalLoggingFilter
    : AbstractGatewayFilterFactory<GlobalLoggingFilter.Config>(Config::class.java) {

    data class Config(
        val baseMessage: String,
        val preLogger: Boolean,
        val postLogger: Boolean,
    )

    private val log = KotlinLogging.logger {}


    override fun apply(config: Config): GatewayFilter {

        return GatewayFilter { exchange: ServerWebExchange, chain: GatewayFilterChain ->
            val request = exchange.request
            val response = exchange.response
            if (config.preLogger) {
                log.info { "[${config.baseMessage}] request id : ${request.id} " }
            }
            chain.filter(exchange).then(Mono.fromRunnable {
                if (config.postLogger) {
                    log.info { "[${config.baseMessage}] response code : ${response.statusCode} " }
                }
            })
        }
    }
}