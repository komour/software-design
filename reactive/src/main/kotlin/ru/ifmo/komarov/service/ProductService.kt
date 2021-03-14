package ru.ifmo.komarov.service

import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import ru.ifmo.komarov.model.Product
import ru.ifmo.komarov.repository.ProductRepository

@Component
class ProductService(private val productRepository: ProductRepository) {
    fun addProduct(product: Product): Mono<Product> {
        return productRepository.insert(product)
    }

    fun getProductById(id: Long): Mono<Product> {
        return productRepository.findById(id)
    }

    fun deleteProductById(id: Long) {
        productRepository.deleteById(id)
    }
}
