package com.mradziewicz.exchange.Controller

import com.mradziewicz.exchange.exception.NotFoundException
import com.mradziewicz.exchange.service.ExchangeRateService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.xml.stream.events.Characters

@RestController
class CurrencyController(var exchangeRateService: ExchangeRateService) {

    @GetMapping("/buyCurrency/{curr}/{amount}")
    fun getBuyExchangeRate(@PathVariable curr: String?, @PathVariable amount: Double): String {
            return exchangeRateService.cantorExchangeCurrencyToPln(curr!!, amount)

    }
    @GetMapping("/sellCurrency/{curr}/{amount}")
    fun getSellExchangeRate(@PathVariable curr: String?, @PathVariable amount: Double): String {
            return exchangeRateService.cantorExchangePlnToCurrency(curr!!, amount);

    }
    @GetMapping("/exchangeTwoCurrency/{sellCurrency}/{buyCyrrency}/{amount}")
    fun getSellExchangeTwoRate(@PathVariable sellCurrency: String, @PathVariable buyCyrrency: String, @PathVariable amount: Double): String{
        return exchangeRateService.cantorExchangeTwoCurrency(sellCurrency, buyCyrrency, amount);
    }
}