package com.example.productservice.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface ProductDao : JpaRepository<ProductEntity, String> {
    fun findByProductId(productId: String) : ProductEntity
}