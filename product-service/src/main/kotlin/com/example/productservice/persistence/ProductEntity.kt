package com.example.productservice.persistence

import javax.persistence.*

@Entity
@Table(name = "product")
data class ProductEntity(
    var productId: String,
    var productName: String,
    var stock: Int,
    var unitPrice: Int,
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
