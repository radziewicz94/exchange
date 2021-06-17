package com.mradziewicz.exchange.service;

import com.mradziewicz.exchange.model.Currency;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ExchangeRateService {
    private RestTemplate restTemplate = new RestTemplate();
    private static final double provision = 0.98;

    public String cantorExchangeCurrencyToPln(String currency, double count) {
        return "Koszt kupna waluty dla kantoru to: " +  cantorBuyCurrency(currency, count);
    }
    public String cantorExchangePlnToCurrency(String currency, double count){
        return "Koszt sprzedaży waluty dla kantoru to: " + cantorSellCurrency(currency, count);
    }
    public String cantorExchangeTwoCurrency(String currency1, String currency2, double count) {
        return "Wymiana " + currency1 + " na " + currency2 +": " + cantorBuyAndSellCurrency(currency1, currency2, count);
    }
    //Kosz sprzedaży waluty obcej i zamiana na Pln
    public BigDecimal cantorBuyCurrency(String currency, double count){
        Currency currencyDto = restTemplate
                .getForObject("http://api.nbp.pl/api/exchangerates/rates/c/{currency}/today/", Currency.class, currency);
        BigDecimal howMuch = new BigDecimal((currencyDto.getRates()[0].getBid() * count) * provision)
                .setScale(2, RoundingMode.HALF_UP);
        return howMuch;
    }
    //Kosz kupna waluty obcej za Pln
    public BigDecimal cantorSellCurrency(String currency, double count){
        Currency currencyDto = restTemplate
                .getForObject("http://api.nbp.pl/api/exchangerates/rates/c/{currency}/today/", Currency.class, currency);
         BigDecimal howMuch = new BigDecimal((count / currencyDto.getRates()[0].getAsk()) * provision)
                .setScale(2, RoundingMode.HALF_UP);
        return howMuch;
    }
    //Obliczamy kosz Sprzedazy naszej waluty + prowizja oraz kupno innej waluty + prowizja
    public BigDecimal cantorBuyAndSellCurrency(String currency1, String currency2, double count){
        Currency firstCurrency = restTemplate
                .getForObject("http://api.nbp.pl/api/exchangerates/rates/c/{currency1}/today/", Currency.class, currency1);
        Currency secondCurrency = restTemplate
                .getForObject("http://api.nbp.pl/api/exchangerates/rates/c/{currency2}/today/", Currency.class, currency2);
        //Obliczamy ile dostaniem Pln za ilość konkretnej waluty + prowizja
        BigDecimal firstCalculate = cantorBuyCurrency(firstCurrency.getCode(), count);
        //Obliczamy i zwracamy ile dostanie za Pln konkretnej waluty + prowizja
        return cantorSellCurrency(secondCurrency.getCode(), firstCalculate.doubleValue());
    }

}
