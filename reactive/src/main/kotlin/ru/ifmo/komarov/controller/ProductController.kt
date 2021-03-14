package ru.ifmo.komarov.controller

import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import ru.ifmo.komarov.model.Product
import ru.ifmo.komarov.service.ProductService


@RestController
class ProductController(private val productService: ProductService) {
    @PostMapping("product")
    fun addProduct(@RequestBody product: Product): Mono<Product> {
        return productService.addProduct(product)
    }

    @GetMapping("product/{id}")
    fun getProduct(@PathVariable id: Long): Mono<Product> {
        return productService.getProductById(id)
    }

    @DeleteMapping("product/{id}/delete")
    fun deleteProduct(@PathVariable id: Long) {
        productService.deleteProductById(id)
    }
}
