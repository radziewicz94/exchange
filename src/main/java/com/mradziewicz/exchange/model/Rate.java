package com.mradziewicz.exchange.model;

public class Rate {
        double bid;
        double ask;

    public Rate() {
    }

    public Rate(double bid, double ask) {
            this.bid = bid;
            this.ask = ask;
        }

        public double getBid() {
            return bid;
        }

        public double getAsk() {
            return ask;
        }

    @Override
    public String toString() {
        return " bid=" + bid + ", ask=" + ask;
    }
}
