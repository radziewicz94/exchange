package com.mradziewicz.exchange.Controller

import com.mradziewicz.exchange.service.ExchangeRateService
import org.springframework.web.bind.annotation.*

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
    @GetMapping("/exchangeTwoCurrency/{sellCurrency}/{buyCurrency}/{amount}")
    fun getSellExchangeTwoRate(@PathVariable sellCurrency: String, @PathVariable buyCurrency: String, @PathVariable amount: Double): String{
        return exchangeRateService.cantorExchangeTwoCurrency(sellCurrency, buyCurrency, amount);
    }
}