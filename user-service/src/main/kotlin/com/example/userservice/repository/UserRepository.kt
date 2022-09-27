package com.example.userservice.repository

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<UserEntity, String> {
    fun findByUserId(userId: String) : UserEntity?
    fun findByEmail(email: String): UserEntity?
}