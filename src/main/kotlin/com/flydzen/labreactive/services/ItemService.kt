package com.flydzen.labreactive.services

import com.flydzen.labreactive.documents.Item
import com.flydzen.labreactive.repository.ItemRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.util.UUID
import reactor.core.publisher.Mono

@Service
class ItemService(
    private val itemRepository: ItemRepository,
    private val userService: UserService,
    private val currencyService: CurrencyService,
) {
    fun addItem(item: Item): Mono<Item> = itemRepository.insert(item)

    fun getItems(userId: UUID): Flux<Item> {
        return userService.getCurrency(userId).flatMapMany { userCurrency ->
            itemRepository.findAll().map { item ->
                item.convert(
                    userCurrency,
                    currencyService.convertBetween(item.price, item.currency, userCurrency)
                )
            }
        }
    }
}