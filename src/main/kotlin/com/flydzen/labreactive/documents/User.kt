package com.flydzen.labreactive.documents

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.Currency
import java.util.UUID

@Document
data class User (
    val currency: Currency,
    val phone: String,
    @Id
    val uid: UUID = UUID.randomUUID(),
)