package com.flydzen.labreactive.controllers

import com.flydzen.labreactive.documents.Item
import com.flydzen.labreactive.services.ItemService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.UUID


@RestController
@RequestMapping("/items")
class ItemsController(private val itemService: ItemService) {
    @PostMapping("/add")
    fun addItem(item: Item): Mono<Void> {
        return itemService.addItem(item).then()
    }

    @GetMapping("/{userId}")
    fun getCurrency(@PathVariable userId: UUID): Flux<Item> {
        return itemService.getItems(userId)
    }
}