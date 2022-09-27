package com.example.userservice.service.dto


data class LoginRequest(
    val email: String,
    val password: String,
) {

    // 일단 이렇게 처리 - @NoArgs 어노테이션 만들것
    constructor() : this("", "")
}
