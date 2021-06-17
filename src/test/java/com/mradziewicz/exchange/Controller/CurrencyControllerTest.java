package com.mradziewicz.exchange.Controller;

import com.mradziewicz.exchange.model.Currency;
import com.mradziewicz.exchange.service.ExchangeRateService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;

@SpringBootTest
@AutoConfigureMockMvc
class CurrencyControllerTest {


    private static final double provision = 0.98;
    TestRestTemplate restTemplate = new TestRestTemplate();

    @Autowired
    ExchangeRateService exchangeRateService;

    @Test
    void should_Return_A_Simple_String(){
        Currency eur = getCurrency("eur");
        Assert.assertEquals(createSimpleText(eur), getCurrency("eur").toString());
    }
    @Test
    void should_exchange_50_euro_to_pln_with_provision(){
        Assert.assertEquals(cantorBuyCurrency("eur", 50), exchangeRateService.cantorBuyCurrency("eur", 50));

    }
    @Test
    void should_exchange_50_pln_to_eur_with_provision()
    {
        Assert.assertEquals(cantorSellCurrency("eur", 50), exchangeRateService.cantorSellCurrency("eur", 50));
    }
    @Test
    void should_exchange_50_usd_to_pln_with_provision()
    {
        Assert.assertEquals(cantorBuyCurrency("usd", 50), exchangeRateService.cantorBuyCurrency("usd", 50));
    }
    @Test
    void should_exchange_50_pln_to_usd_with_provision()
    {
        Assert.assertEquals(cantorSellCurrency("usd", 50), exchangeRateService.cantorSellCurrency("usd", 50));
    }
    @Test
    void should_exchange_50_gdb_to_pln_with_provision()
    {
        Assert.assertEquals(cantorBuyCurrency("gbp", 50), exchangeRateService.cantorBuyCurrency("gbp", 50));
    }
    @Test
    void should_exchange_50_usd_to_eur_with_provision()
    {
        Assert.assertEquals(cantorSellCurrency("gbp", 50), exchangeRateService.cantorSellCurrency("gbp", 50));
    }
    @Test
    void should_exchange_50_eur_to_usd_with_provision()
    {
        Assert.assertEquals(cantorBuyAndSellCurrency("eur", "usd", 50),
                exchangeRateService.cantorBuyAndSellCurrency("eur", "usd", 50));
    }
    @Test
    void should_exchange_50_eur_to_gdb_with_provision()
    {
        Assert.assertEquals(cantorBuyAndSellCurrency("eur","gbp", 50)
                , exchangeRateService.cantorBuyAndSellCurrency("eur", "gbp", 50));
    }
    @Test
    void should_exchange_50_usd_to_gdb_with_provision()
    {
        Assert.assertEquals(cantorBuyAndSellCurrency("usd","gbp", 50)
                , exchangeRateService.cantorBuyAndSellCurrency("usd","gbp", 50));
    }
    @Test
    void should_exchange_50_gdb_to_eur_with_provision()
    {
        Assert.assertEquals(cantorBuyAndSellCurrency("gbp" ,"eur", 50)
                , exchangeRateService.cantorBuyAndSellCurrency("gbp","eur", 50));
    }
    @Test
    void should_exchange_50_gdb_to_usd_with_provision()
    {
        Assert.assertEquals(cantorBuyAndSellCurrency("gbp" ,"usd", 50)
                , exchangeRateService.cantorBuyAndSellCurrency("gbp","usd", 50));
    }

    private Currency getCurrency(String currency){
        Currency testRest = restTemplate.getForObject("http://api.nbp.pl/api/exchangerates/rates/c/{currency}/today/"
                , Currency.class, currency);
        return testRest;
    }

    private BigDecimal cantorBuyCurrency(String currency, double count){
        Currency currencyDto = restTemplate
                .getForObject("http://api.nbp.pl/api/exchangerates/rates/c/{currency}/today/", Currency.class, currency);
        BigDecimal howMuch = new BigDecimal((currencyDto.getRates()[0].getBid() * count) * provision)
                .setScale(2, RoundingMode.HALF_UP);
        return howMuch;
    }
    private BigDecimal cantorSellCurrency(String currency, double count){
        Currency currencyDto = restTemplate
                .getForObject("http://api.nbp.pl/api/exchangerates/rates/c/{currency}/today/", Currency.class, currency);
        BigDecimal howMuch = new BigDecimal((count / (currencyDto.getRates()[0].getAsk()))* provision)
                .setScale(2, RoundingMode.HALF_UP);
        return howMuch;
    }
    private BigDecimal cantorBuyAndSellCurrency(String currency1, String currency2, double count){
        Currency firstCurrency = restTemplate
                .getForObject("http://api.nbp.pl/api/exchangerates/rates/c/{currency1}/today/", Currency.class, currency1);
        Currency secondCurrency = restTemplate
                .getForObject("http://api.nbp.pl/api/exchangerates/rates/c/{currency2}/today/", Currency.class, currency2);
        //Obliczamy ile dostaniem Pln za ilość konkretnej waluty + prowizja
        BigDecimal firstCalculate = cantorBuyCurrency(firstCurrency.getCode(), count);
        //Obliczamy i zwracamy ile dostanie za Pln konkretnej waluty + prowizja
        return cantorSellCurrency(secondCurrency.getCode(), firstCalculate.doubleValue());
    }

    private String createSimpleText(Currency currency) {

        return  "code=" + currency.getCode() + ", " + "" +
                "rate=[ bid=" + currency.getRates()[0].getBid() + ", ask=" + currency.getRates()[0].getAsk() + "]";
    }
}