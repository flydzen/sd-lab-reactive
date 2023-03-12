package com.flydzen.labreactive.controllers

import com.flydzen.labreactive.documents.User
import com.flydzen.labreactive.services.UserService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import java.util.Currency
import java.util.UUID


@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {
    @GetMapping("/{userId}")
    fun getUser(@PathVariable userId: UUID): Mono<User?> {
        return userService.getUser(userId)
    }

    @PostMapping("/")
    fun createUser(phone: String, currency: Currency): Mono<User> {
        return userService.createUser(currency, phone)
    }

    @GetMapping("/{userId}/currency")
    fun getCurrency(@PathVariable userId: UUID): Mono<Currency> {
        return userService.getCurrency(userId)
    }
}