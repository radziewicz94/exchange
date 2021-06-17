package com.mradziewicz.exchange.model;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Currency {
    private String code;
    private Rate rates[];

    public Currency() {
    }

    public Currency(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public Rate[] getRates() {
        return rates;
    }

    @Override
    public String toString() {
        return "code=" + code + ", " +"rate=" + Arrays.toString(rates);
    }
}
