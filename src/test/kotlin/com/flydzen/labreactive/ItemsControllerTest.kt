package com.flydzen.labreactive

import com.flydzen.labreactive.controllers.ItemsController
import com.flydzen.labreactive.controllers.UserController
import com.flydzen.labreactive.documents.Item
import com.flydzen.labreactive.documents.User
import com.flydzen.labreactive.repository.ItemRepository
import com.flydzen.labreactive.repository.UserRepository
import com.flydzen.labreactive.services.ItemService
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
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.math.BigDecimal
import java.util.*


@WebFluxTest(controllers = [ItemsController::class])
@AutoConfigureDataMongo
@ImportAutoConfiguration
class ItemsControllerTest {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @MockBean
    private lateinit var itemService: ItemService

    @MockBean
    private lateinit var repository: ItemRepository

    @Test
    fun testAddItemEndpoint() {
        val item = Item(BigDecimal.TEN, Currency.getInstance("RUB"), "headphones")

        Mockito.`when`(itemService.addItem(item))
            .thenReturn(Mono.empty())

        webTestClient.post().uri("/items/add")
            .bodyValue(item)
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun testGetItemsEndpoint() {
        val items = arrayOf(
            Item(BigDecimal.TEN, Currency.getInstance("RUB"), "headphones"),
            Item(BigDecimal.ONE, Currency.getInstance("USD"), "legs"),
        )
        val uid = UUID.randomUUID()
        Mockito.`when`(itemService.getItems(uid))
            .thenReturn(Flux.just(items[0], items[1]))

        webTestClient.get().uri("/items/$uid")
            .exchange()
            .expectStatus().isOk
            .expectBody<List<Item>>()
    }
}