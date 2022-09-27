package com.example.userservice.service

import com.example.userservice.client.OrderServiceClient
import com.example.userservice.repository.UserEntity
import com.example.userservice.repository.UserRepository
import com.example.userservice.service.dto.CreateUserRequest
import com.example.userservice.service.dto.CreateUserResponse
import com.example.userservice.service.dto.GetDetailedUserResponse
import com.example.userservice.service.dto.GetUsersResponse
import org.springframework.core.env.Environment
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val restTemplate: RestTemplate,
    private val orderServiceClient: OrderServiceClient,
    private val env: Environment,
) {

    @Transactional
    fun createUser(createUserRequest: CreateUserRequest): CreateUserResponse {
        val user = UserEntity(
            getNextUserId(),
            createUserRequest.name,
            createUserRequest.email,
            createUserRequest.pwd
        )


        user.encryptPwd(passwordEncoder::encode)
        val savedUser = userRepository.save(user)

        return CreateUserResponse(
            savedUser.name,
            savedUser.email,
            savedUser.userId
        )
    }

    @Transactional(readOnly = true)
    fun getDetailedUserById(userId: String): GetDetailedUserResponse {
        val user = userRepository.findByUserId(userId)
            ?: throw IllegalArgumentException("no such user id : $userId")

        /*val orderUrl = String.format(env.getProperty("order-service.url")!!, userId)
        val orderListResponse: ResponseEntity<List<GetDetailedUserResponse.OrderResponse>> = restTemplate.exchange(
            orderUrl,
            HttpMethod.GET,
            null,
            object : ParameterizedTypeReference<List<GetDetailedUserResponse.OrderResponse>>() {} // 익명 클래스
        )*/

        val orderListResponse = orderServiceClient.getOrders(userId)

        return GetDetailedUserResponse(
            user.name,
            user.email,
            user.userId,
            orderListResponse
        )
    }

    @Transactional(readOnly = true)
    fun getAll(): List<GetUsersResponse> {
        return userRepository.findAll().map { user ->
            GetUsersResponse(
                user.name,
                user.email,
                user.userId
            )
        }
    }


    private fun getNextUserId(): String {
        return java.util.UUID.randomUUID().toString()
    }
}