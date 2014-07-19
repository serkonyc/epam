/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.bursethread.data;

import java.util.HashSet;
import org.epam.bursethread.entity.broker.Broker;

/**
 *
 * @author Sergiusz
 */
public class TradersData {

    private final HashSet<Broker> TRADERS;

    public TradersData() {
        TRADERS = new HashSet<>();
        TRADERS.add(new Broker("Sasha", 12, 10, "buy", "IBM", 11));
        TRADERS.add(new Broker("Vanya", 4, 2, "sell", "IBA", 5));
        TRADERS.add(new Broker("Sava", 5, 2, "buy", "EPAM", 7));
        TRADERS.add(new Broker("Vasya", 13, 9, "sell", "IBM", 4));
        TRADERS.add(new Broker("Valera", 13, 7, "sell", "MS", 6));
        TRADERS.add(new Broker("Vasya2", 16, 9, "buy", "MS", 3));
        TRADERS.add(new Broker("Vasya3", 16, 12, "sell", "IBM", 6));
        TRADERS.add(new Broker("Vasya4", 10, 8, "buy", "MS", 5));
        TRADERS.add(new Broker("Vasya5", 16, 9, "sell", "EPAM", 5));
    }

    public HashSet<Broker> getTraders() {
        return TRADERS;
    }

}
