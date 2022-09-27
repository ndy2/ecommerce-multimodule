package com.example.productservice.persistence

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "product")
data class ProductEntity(
    @Id var productId: String?,
    var productName: String,
    var stock: Int,
    var unitPrice: Int,
)
