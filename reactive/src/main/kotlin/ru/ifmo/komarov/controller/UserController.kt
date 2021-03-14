package ru.ifmo.komarov.controller

import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.ifmo.komarov.model.Product
import ru.ifmo.komarov.model.User
import ru.ifmo.komarov.service.UserService


@RestController
class UserController(private val userService: UserService) {
    @PostMapping(value = ["user"])
    fun addUser(@RequestBody user: User): Mono<User> {
        return userService.addUser(user)
    }

    @GetMapping(value = ["user/{id}"])
    fun getUser(@PathVariable id: Long): Mono<User> {
        return userService.getUserById(id)
    }

    @DeleteMapping(value = ["user/{id}/delete"])
    fun deleteUser(@PathVariable id: Long) {
        userService.deleteUserById(id)
    }

    @GetMapping(value = ["user/{id}/products"])
    fun getProducts(@PathVariable id: Long): Flux<Product> {
        return userService.getUserProducts(id)
    }
}