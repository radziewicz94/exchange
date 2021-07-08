package com.mradziewicz.exchange

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class ExchangeApplication

    fun main(args: Array<String>) {
        runApplication<ExchangeApplication>(*args)
    }
