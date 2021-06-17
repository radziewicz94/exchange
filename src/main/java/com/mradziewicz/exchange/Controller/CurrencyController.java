package com.mradziewicz.exchange.Controller;

import com.mradziewicz.exchange.service.ExchangeRateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyController {

    private final ExchangeRateService exchangeRateService;

    public CurrencyController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    //Sprzedajemy Obcą walutę
    @GetMapping("/buyCurrency/{curr}/{count}")
    public String getBuyExchangeRate(@PathVariable String curr, @PathVariable double count)  {
        return exchangeRateService.cantorExchangeCurrencyToPln(curr, count);
    }
    //Kupujemy obcą walutę
    @GetMapping("/sellCurrency/{curr}/{count}")
    public String getSellExchangeRate(@PathVariable String curr, @PathVariable double count){
        return exchangeRateService.cantorExchangePlnToCurrency(curr, count);
    }
    @GetMapping("/exchangeTwoCurrency/{curr1}/{curr2}/{count}")
    public String getSellExchangeTwoRate(@PathVariable String curr1, @PathVariable String curr2, @PathVariable double count){
        return exchangeRateService.cantorExchangeTwoCurrency(curr1, curr2, count);
    }
}
