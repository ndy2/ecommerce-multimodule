package com.example.userservice.service.dto

data class CreateUserResponse(
    val name: String,
    val email: String,
    val userId: String,
)
