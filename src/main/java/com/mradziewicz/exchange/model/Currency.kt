package com.mradziewicz.exchange.model

import org.springframework.stereotype.Component

@Component
data class Currency(var code: String, var rates: List<Rate>) {
    constructor(): this("", emptyList())

}