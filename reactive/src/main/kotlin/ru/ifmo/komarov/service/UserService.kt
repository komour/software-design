package ru.ifmo.komarov.service

import ru.ifmo.komarov.model.Product
import ru.ifmo.komarov.model.User
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.ifmo.komarov.repository.*
import ru.ifmo.komarov.utils.CurrencyConverter


@Component
class UserService(
    private val userRepository: UserRepository,
    private val productRepository: ProductRepository
) {
    fun addUser(user: User): Mono<User> {
        return userRepository.insert(user)
    }

    fun getUserById(id: Long): Mono<User> {
        return userRepository.findById(id)
    }

    fun deleteUserById(id: Long) {
        userRepository.deleteById(id)
    }

    fun getUserProducts(id: Long): Flux<Product> {
        return userRepository.findById(id)
            .flatMapMany { user ->
                productRepository.findAll()
                    .map { product -> CurrencyConverter.convert(product, user.accountCurrency) }
            }
    }
}
