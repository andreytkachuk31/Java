package com.google.gwt.sample.stockwatcher.client;

import java.io.Serializable;

public class StockPrice implements Serializable {

    private String stockName;
    private double price;
    private double change;

    public StockPrice() {

    }

    public StockPrice(String stockName, double price, double change) {
        this.stockName = stockName;
        this.price = price;
        this.change = change;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public double getChangePercent() {
        return 10.0 * this.change / this.price;
    }
}