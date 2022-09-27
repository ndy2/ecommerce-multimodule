package com.example.userservice.service.dto

data class CreateUserRequest(
    val email: String,
    val pwd: String,
    val name: String,
)
