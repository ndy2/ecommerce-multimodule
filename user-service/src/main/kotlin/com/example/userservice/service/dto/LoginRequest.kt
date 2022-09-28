package com.example.userservice.service.dto

import com.example.util.NoArgs

@NoArgs
data class LoginRequest(
    val email: String,
    val password: String,
)
