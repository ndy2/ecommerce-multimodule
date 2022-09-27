package com.example.apigateway.filter

import io.jsonwebtoken.Jwts
import mu.KotlinLogging
import org.apache.commons.lang.StringUtils
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.core.env.Environment
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class AuthorizationHeaderFilter(
    private val env: Environment
) : AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config>(Config::class.java) {

    class Config

    private val log = KotlinLogging.logger {}

    override fun apply(config: Config?): GatewayFilter {

        return GatewayFilter { exchange: ServerWebExchange, chain: GatewayFilterChain ->
            val request = exchange.request

            // authorization header 확인
            if (!request.headers.containsKey(HttpHeaders.AUTHORIZATION)) {
                return@GatewayFilter onError(
                    exchange,
                    "no AUTHORIZATION header",
                    HttpStatus.UNAUTHORIZED
                )
            }

            // Bearer 토큰 확인
            val authorizationHeader = request.headers[HttpHeaders.AUTHORIZATION]!![0]
            if (!authorizationHeader.startsWith("Bearer ")) {
                return@GatewayFilter onError(exchange, "no bearer token", HttpStatus.UNAUTHORIZED)
            }

            // jwt 유효성 확인
            val jwt = authorizationHeader.substring(7)
            if (!isValidJwt(jwt)) {
                return@GatewayFilter onError(exchange, "invalid token", HttpStatus.UNAUTHORIZED)
            }
            chain.filter(exchange)
        }
    }

    private fun isValidJwt(jwt: String): Boolean {
        var result = true
        var subject: String? = null
        try {
            subject = Jwts.parser()
                .setSigningKey(env.getProperty("jwt.secret"))
                .parseClaimsJws(jwt).body
                .subject
        } catch (ex: Exception) {
            result = false
        }
        if (StringUtils.isEmpty(subject)) {
            result = false
        }
        return result
    }

    private fun onError(exchange: ServerWebExchange, errorMessage: String, status: HttpStatus): Mono<Void?>? {
        log.info { "errorMessage : $errorMessage" }

        val response = exchange.response
        response.statusCode = status
        return response.setComplete()
    }
}