package com.flydzen.labreactive

import java.util.*

class UnsupportedCurrencyException(currency: Currency): Exception("Unsupported currency $currency")

class UserNotFoundException(userId: UUID): Exception("User with uid $userId not found")
