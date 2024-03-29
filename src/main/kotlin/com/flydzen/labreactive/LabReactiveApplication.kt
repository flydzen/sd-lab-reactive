package com.flydzen.labreactive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@SpringBootApplication
@EnableReactiveMongoRepositories
class LabReactiveApplication

fun main(args: Array<String>) {
    runApplication<LabReactiveApplication>(*args)
}
