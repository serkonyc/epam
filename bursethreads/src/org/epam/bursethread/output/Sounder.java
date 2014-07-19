/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.bursethread.output;

import java.util.List;
import org.epam.bursethread.entity.broker.Broker;
import org.epam.bursethread.entity.stock.Stock;

/**
 *
 * @author Sergiusz
 */
public class Sounder {

    private StringBuffer history = new StringBuffer();

    public String getHistory() {
        return history.toString();
    }

    public void appendTrader(Broker broker) {
        history.append(broker.getNAME()).append("\twas  ADDED.\n");
    }

    public void appendDeal(Broker trader1, Broker trader2, Stock stock) {
        history.append("Сделка проведена: ").append(trader1.getNAME())
                .append(" и ").append(trader2.getNAME()).append(" с новой ценой для ")
                .append(stock.getNAME()).append(" ")
                .append(String.format("%.2f", stock.getQuote())).append("\n");
    }

    public void removeTrader(Broker broker) {
        history.append(broker.getNAME()).append("\twas  GONE.\n");
    }

    public void appendTradeResult(List<Stock> stocks) {
        history.append("\n--------Result--------\n");
        stocks.stream().forEach((stock) -> {
            history.append(stock.toString()).append("\n");
        });
    }

    public void clearHistory() {
        history = new StringBuffer();
    }

}
