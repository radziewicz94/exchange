package com.mradziewicz.exchange.service

import com.mradziewicz.exchange.model.Currency
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.math.BigDecimal

@Service
class ExchangeRateService {

    @Autowired
    private lateinit var calculate: Calculate

    fun cantorExchangeCurrencyToPln(currency: String, amount: Double): String {
            return "Koszt kupna waluty dla kantoru to: " + cantorBuyCurrency(currency, amount)
    }
    fun cantorExchangePlnToCurrency(currency: String,amount: Double): String{
        return "Koszt sprzedaży waluty dla kantoru to: " + cantorSellCurrency(currency, amount)
    }
    fun cantorExchangeTwoCurrency(currency1: String, currency2: String, amount: Double): String {
        return "Wymiana " + currency1 + " na " + currency2 +": " + cantorBuyAndSellCurrency(currency1, currency2, amount);
    }
    fun cantorBuyCurrency(currency: String, amount: Double): BigDecimal{
        val currency = getCurrencyFromNbp(currency)
        val howMuch = calculate.calculateExchangePlnToCurrency(currency!!.rates[0].ask, amount)

        return howMuch;
    }
     fun cantorSellCurrency(currency: String, amount: Double): BigDecimal{
        val currency = getCurrencyFromNbp(currency)
        val howMuch = calculate.calculateExchangeCurrencyToPln(currency!!.rates[0].bid, amount)
        return howMuch;
    }
    fun cantorBuyAndSellCurrency(sellCurrency: String, buyCurrency: String, amount: Double): BigDecimal{
        val firstCurrency = getCurrencyFromNbp(sellCurrency)
        val secondCurrency = getCurrencyFromNbp(buyCurrency)
        val firstCalculate = cantorBuyCurrency(firstCurrency!!.code , amount);
        return cantorSellCurrency(secondCurrency!!.code, firstCalculate.toDouble());
    }
    fun getCurrencyFromNbp(currency: String): Currency? {
        val restTemplate = RestTemplate()
        val getCurrency = restTemplate
            .getForObject("http://api.nbp.pl/api/exchangerates/rates/c/{currency1}/today/", Currency::class.java, currency)
        return getCurrency
    }

}

