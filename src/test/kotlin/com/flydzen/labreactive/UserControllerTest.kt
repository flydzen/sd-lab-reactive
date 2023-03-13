package com.flydzen.labreactive

import com.flydzen.labreactive.controllers.UserController
import com.flydzen.labreactive.documents.User
import com.flydzen.labreactive.repository.UserRepository
import com.flydzen.labreactive.services.UserService
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import reactor.core.publisher.Mono
import java.util.*


@WebFluxTest(controllers = [UserController::class])
@AutoConfigureDataMongo
@ImportAutoConfiguration
class UserControllerTest {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @MockBean
    private lateinit var userService: UserService

    @MockBean
    private lateinit var repository: UserRepository

    @Test
    fun testCreateUserEndpoint() {
        val user = User(Currency.getInstance("RUB"), "123")
        Mockito.`when`(userService.createUser(user.currency, user.phone))
            .thenReturn(Mono.just(user))

        webTestClient.post().uri("/users?currency=RUB&phone=123")
            .exchange()
            .expectStatus().isOk
            .expectBody<User>().isEqualTo(user)
    }

    @Test
    fun testGetUserEndpoint() {
        val user = User(Currency.getInstance("RUB"), "123")
        Mockito.`when`(userService.getUser(user.uid))
            .thenReturn(Mono.just(user))

        webTestClient.get().uri("/users/${user.uid}")
            .exchange()
            .expectStatus().isOk
            .expectBody<User>().isEqualTo(user)
    }

    @Test
    fun testGetUserCurrency() {
        val user = User(Currency.getInstance("RUB"), "123")
        Mockito.`when`(userService.getCurrency(user.uid))
            .thenReturn(Mono.just(user.currency))

        webTestClient.get().uri("/users/${user.uid}/currency")
            .exchange()
            .expectStatus().isOk
            .expectBody<Currency>().isEqualTo(user.currency)
    }
}