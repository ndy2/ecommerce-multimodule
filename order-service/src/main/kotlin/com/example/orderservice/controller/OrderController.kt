package com.example.orderservice.controller

import com.example.orderservice.controller.dto.CreateOrderRequest
import com.example.orderservice.controller.dto.OrderResponse
import com.example.orderservice.jpa.OrderDao
import com.example.orderservice.jpa.OrderEntity
import com.example.orderservice.messagequeue.KafkaProducer
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class OrderController(
        private val orderDao: OrderDao,
        private val kafkaProducer: KafkaProducer
) {

    @PostMapping("/{userId}/orders")
    fun createOrder(
            @PathVariable userId: String,
            @RequestBody request: CreateOrderRequest,
    ): OrderResponse {
        println("create order request of userID : ${userId}, request : ${request}")
        val orderId = UUID.randomUUID().toString()
        val totalPrice = request.unitPrice * request.quantity

        val orderEntity = orderDao.save(OrderEntity(
                orderId,
                request.quantity,
                request.unitPrice,
                totalPrice,
                request.productId,
                userId
        ))

        kafkaProducer.send("example-catalog-topic", orderEntity)
        return OrderResponse.ofEntity(orderEntity)
    }

    @GetMapping("/orders/{orderId}")
    fun getByOrderId(@PathVariable orderId: String): OrderResponse {

        return OrderResponse.ofEntity(orderDao.findByOrderId(orderId))
    }

    @GetMapping("/{userId}/orders")
    fun getByUserId(@PathVariable userId: String): List<OrderResponse> {
        return orderDao.findByUserId(userId)
                .map { entity -> OrderResponse.ofEntity(entity) }
                .toList()
    }

}