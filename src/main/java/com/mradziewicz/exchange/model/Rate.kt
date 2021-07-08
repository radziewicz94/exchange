package com.mradziewicz.exchange.model

import org.springframework.stereotype.Component

@Component
data class Rate(var bid: Double, var ask: Double) {
    constructor(): this(0.0, 0.0)
}