package com.mradziewicz.exchange.Controller

import com.mradziewicz.exchange.model.Currency
import com.mradziewicz.exchange.model.Rate
import com.mradziewicz.exchange.service.Calculate
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.math.BigDecimal
import java.math.RoundingMode

@RunWith(SpringRunner::class)
@SpringBootTest
class CurrencyControllerTestK {

    @Autowired
    lateinit var calculate: Calculate

    @Test
    fun should_exchange_50_pln_to_eur_with_provision(){
        val sellEuro = BigDecimal(10.79).setScale(2, RoundingMode.HALF_UP)
        Assert.assertEquals(sellEuro, calculate.calculateExchangePlnToCurrency(4.54, 50.0));
    }
    @Test
    fun should_exchange_20_54_pln_to_eur_with_provision(){
        val sellEuro = BigDecimal(4.43).setScale(2, RoundingMode.HALF_UP)
        Assert.assertEquals(sellEuro, calculate.calculateExchangePlnToCurrency(4.54, 20.54));
    }
    @Test
    fun should_exchange_50_eur_to_pln_with_provision(){
        val buyEur = BigDecimal(218.05).setScale(2, RoundingMode.HALF_UP)
        Assert.assertEquals(buyEur, calculate.calculateExchangeCurrencyToPln(4.45, 50.0));
    }
    @Test
    fun should_exchange_32_97_eur_to_pln_with_provision(){
        val buyEur = BigDecimal(146.69).setScale(2, RoundingMode.HALF_UP)
        Assert.assertEquals(buyEur, calculate.calculateExchangeCurrencyToPln(4.54, 32.97));
    }
    @Test
    fun should_exchange_50_pln_to_usd_with_provision(){
        val sellUsd = BigDecimal(12.79).setScale(2, RoundingMode.HALF_UP)
        Assert.assertEquals(sellUsd, calculate.calculateExchangePlnToCurrency(3.83, 50.0));
    }
    @Test
    fun should_exchange_20_54_pln_to_usd_with_provision(){
        val sellUsd = BigDecimal(5.26).setScale(2, RoundingMode.HALF_UP)
        Assert.assertEquals(sellUsd, calculate.calculateExchangePlnToCurrency(3.83, 20.54));
    }
    @Test
    fun should_exchange_50_usd_to_pln_with_provision(){
        val buyUsd = BigDecimal(185.71).setScale(2, RoundingMode.HALF_UP)
        Assert.assertEquals(buyUsd, calculate.calculateExchangeCurrencyToPln(3.79, 50.0));
    }
    @Test
    fun should_exchange_32_97_usd_to_pln_with_provision(){
        val buyUsd = BigDecimal(122.46).setScale(2, RoundingMode.HALF_UP)
        Assert.assertEquals(buyUsd, calculate.calculateExchangeCurrencyToPln(3.79, 32.97));
    }
    @Test
    fun should_exchange_50_pln_to_gbp_with_provision(){
        val sellGbp = BigDecimal(9.23).setScale(2, RoundingMode.HALF_UP)
        Assert.assertEquals(sellGbp, calculate.calculateExchangePlnToCurrency(5.31, 50.0));
    }
    @Test
    fun should_exchange_20_54_pln_to_gbp_with_provision(){
        val sellGbp = BigDecimal(3.79).setScale(2, RoundingMode.HALF_UP)
        Assert.assertEquals(sellGbp, calculate.calculateExchangePlnToCurrency(5.31, 20.54));
    }
    @Test
    fun should_exchange_50_gbp_to_pln_with_provision(){
        val buyGdb = BigDecimal(254.80).setScale(2, RoundingMode.HALF_UP)
        Assert.assertEquals(buyGdb, calculate.calculateExchangeCurrencyToPln(5.20, 50.0));
    }
    @Test
    fun should_exchange_32_97_gbp_to_pln_with_provision(){
        val buyGdb = BigDecimal(168.02).setScale(2, RoundingMode.HALF_UP)
        Assert.assertEquals(buyGdb, calculate.calculateExchangeCurrencyToPln(5.20, 32.97));
    }
    @Test
    fun should_exchange_50_gbp_to_usd_with_provision(){
        val exchangeToPln = calculate.calculateExchangeCurrencyToPln(5.20, 50.0)
        val calculateExchangePlnToCurrency = calculate.calculateExchangePlnToCurrency(3.83, exchangeToPln.toDouble())
        val buyUsd = BigDecimal(65.20).setScale(2, RoundingMode.HALF_UP)
        Assert.assertEquals(buyUsd, calculateExchangePlnToCurrency)
    }
    @Test
    fun should_exchange_32_97_gbp_to_usd_with_provision(){
        val exchangeToPln = calculate.calculateExchangeCurrencyToPln(5.20, 92.97)
        val calculateExchangePlnToCurrency = calculate.calculateExchangePlnToCurrency(3.83, exchangeToPln.toDouble())
        val buyUsd = BigDecimal(121.23).setScale(2, RoundingMode.HALF_UP)
        Assert.assertEquals(buyUsd, calculateExchangePlnToCurrency)
    }
    @Test
    fun should_exchange_50_eur_to_usd_with_provision(){
        val exchangeToPln = calculate.calculateExchangeCurrencyToPln(4.45, 50.0)
        val calculateExchangePlnToCurrency = calculate.calculateExchangePlnToCurrency(3.83, exchangeToPln.toDouble())
        val buyUsd = BigDecimal(55.79).setScale(2, RoundingMode.HALF_UP)
        Assert.assertEquals(buyUsd, calculateExchangePlnToCurrency)
    }
    @Test
    fun should_exchange_50_usd_to_gbp_with_provision(){
        val exchangeToPln = calculate.calculateExchangeCurrencyToPln( 3.76, 50.0)
        val calculateExchangePlnToCurrency = calculate.calculateExchangePlnToCurrency(5.31, exchangeToPln.toDouble())
        val buyGbp = BigDecimal(34.00).setScale(2, RoundingMode.HALF_UP)
        Assert.assertEquals(buyGbp, calculateExchangePlnToCurrency)
    }
    @Test
    fun should_return_a_simple_string(){
        val currency = Currency("eur", rates = listOfNotNull(Rate(4.54 ,4.45)))
        val except =  "Currency(code=${currency.code}" +
                ", rates=[Rate(bid=${currency.rates[0].bid}, ask=${currency.rates[0].ask})])";
        Assert.assertEquals(except, currency.toString())

    }

}