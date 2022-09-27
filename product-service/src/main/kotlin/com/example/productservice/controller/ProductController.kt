package com.example.productservice.controller

import com.example.productservice.persistence.ProductDao
import com.example.productservice.persistence.ProductEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/products")
@RestController
class ProductController(
    private val productDao: ProductDao
) {

    @GetMapping
    fun getCatalogs(): List<ProductEntity> {
        return productDao.findAll()
    }
}