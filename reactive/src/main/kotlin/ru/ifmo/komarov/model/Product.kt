package ru.ifmo.komarov.model

import org.springframework.data.annotation.Id
import java.beans.ConstructorProperties

class Product @ConstructorProperties("id", "name", "currency", "price") constructor(
    @Id val id: Long,
    val name: String,
    val currency: Currency,
    val price: Double
)
