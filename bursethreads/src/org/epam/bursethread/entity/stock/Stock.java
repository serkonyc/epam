/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.bursethread.entity.stock;

/**
 *
 * @author Sergiusz
 */
public class Stock {

    private double minPrice;
    private double maxPrice;
    private final String NAME;
    private int number;

    public Stock(int quote, String name, int number) {
        minPrice = quote;
        maxPrice = quote;
        this.NAME = name;
        this.number = number;
    }

    public double getQuote() {
        return (minPrice + maxPrice) / 2;
    }

    public String getNAME() {
        return NAME;
    }

    public int getNumber() {
        return number;
    }

    public void wasTraded(double tradePrice, int number) {
        if (tradePrice <= this.minPrice) {
            minPrice = tradePrice * (this.number - number) / this.number;
            this.number += number;
        } else if (tradePrice >= this.maxPrice) {
            maxPrice = tradePrice * (this.number + number) / this.number;
            this.number -= number;
        } else {
            this.number += number / 2;
        }
    }

    @Override
    public String toString() {
        StringBuilder toString = new StringBuilder();
        toString.append(NAME).append("\t").append(String.format("%.2f", this.getQuote())).append("\tn: ")
                .append(number);
        return toString.toString();
    }

}
