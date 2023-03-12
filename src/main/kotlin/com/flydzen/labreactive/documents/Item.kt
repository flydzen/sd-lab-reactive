package com.flydzen.labreactive.documents

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.util.*

@Document
data class Item (
    val price: BigDecimal,
    val currency: Currency,
    val title: String,
    @Id
    val uid: UUID = UUID.randomUUID(),
) {
    fun convert(currency: Currency, price: BigDecimal): Item = Item(price, currency, title, uid)
}