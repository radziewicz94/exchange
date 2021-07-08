package com.mradziewicz.exchange.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode

@Service
class Calculate {

    val provision = 0.98

    fun calculateExchangePlnToCurrency(currency: Double, amount: Double): BigDecimal{
        val howMuch = BigDecimal((amount / currency) * provision)
            .setScale(2, RoundingMode.HALF_UP);
        return howMuch
    }
    fun calculateExchangeCurrencyToPln(currency: Double, amount: Double): BigDecimal{
        val howMuch = BigDecimal((currency * amount) * provision)
            .setScale(2, RoundingMode.HALF_UP);
        return howMuch
    }
}