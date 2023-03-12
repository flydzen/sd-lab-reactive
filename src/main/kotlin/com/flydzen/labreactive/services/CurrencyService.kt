package com.flydzen.labreactive.services

import com.flydzen.labreactive.UnsupportedCurrencyException
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.util.Currency

@Component
class CurrencyService {
    val currencyMapper = mapOf(
        Currency.getInstance("RUB") to 1,
        Currency.getInstance("USD") to 70,
        Currency.getInstance("EUR") to 80,
    )

    fun convertBetween(amount: BigDecimal, currencyFrom: Currency, currencyTo: Currency): BigDecimal {
        val rateFrom = currencyMapper.getOrElse(currencyFrom) {
            throw UnsupportedCurrencyException(currencyFrom)
        }.toBigDecimal()
        val rateTo = currencyMapper.getOrElse(currencyTo) {
            throw UnsupportedCurrencyException(currencyTo)
        }.toBigDecimal()

        return amount * rateFrom / rateTo
    }

    fun checkCurrency(currency: Currency): Boolean = currencyMapper.containsKey(currency)
}