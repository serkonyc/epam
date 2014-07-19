/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.bursethread.data;

import java.util.ArrayList;
import org.epam.bursethread.entity.stock.Stock;

/**
 *
 * @author Sergiusz
 */
public class StocksData {

    private ArrayList<Stock> STOCKS = new ArrayList<>();

    public StocksData() {
        STOCKS.add(new Stock(12, "IBM", 200));
        STOCKS.add(new Stock(3, "IBA", 140));
        STOCKS.add(new Stock(4, "EPAM", 120));
        STOCKS.add(new Stock(12, "MS", 212));
        STOCKS.add(new Stock(14, "IT", 321));
        STOCKS.add(new Stock(12, "BSU", 201));
        STOCKS.add(new Stock(12, "BSUIR", 202));

    }

    public ArrayList<Stock> getStocks() {
        return STOCKS;
    }

}
