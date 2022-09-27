package com.example.userservice.service.dto

data class GetDetailedUserResponse(
    val name: String,
    val email: String,
    val userId: String,
    val orders: List<OrderResponse>
) {

    data class OrderResponse(
        val orderId: String,
        val productId: String,
        val quantity: Int,
        val unitPrice: Int,
        val totalPrice: Int,
    )
}