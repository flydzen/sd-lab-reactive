package com.flydzen.labreactive.services

import com.flydzen.labreactive.UnsupportedCurrencyException
import com.flydzen.labreactive.UserNotFoundException
import com.flydzen.labreactive.documents.User
import com.flydzen.labreactive.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.UUID
import reactor.core.publisher.Mono
import java.util.Currency
import reactor.kotlin.core.publisher.switchIfEmpty

@Service
class UserService(
    private val repository: UserRepository,
    private val currencyService: CurrencyService,
) {
    fun getUser(userId: UUID): Mono<User?> = repository.findById(userId)

    fun createUser(currency: Currency, phone: String): Mono<User> {
        return if (currencyService.checkCurrency(currency))
            repository.save(User(currency, phone))
        else
            Mono.error(UnsupportedCurrencyException(currency))
    }

    fun getCurrency(userId: UUID): Mono<Currency> = repository
        .findById(userId)
        .switchIfEmpty { Mono.error { UserNotFoundException(userId) } }
        .map { it!!.currency }
}