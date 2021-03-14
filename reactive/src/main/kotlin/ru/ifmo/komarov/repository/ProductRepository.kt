package ru.ifmo.komarov.repository

import ru.ifmo.komarov.model.Product
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : ReactiveMongoRepository<Product, Long>