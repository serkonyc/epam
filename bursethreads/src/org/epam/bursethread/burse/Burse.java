/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.bursethread.burse;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import org.apache.log4j.Logger;
import org.epam.bursethread.control.Main;
import org.epam.bursethread.data.StocksData;
import org.epam.bursethread.data.TradersData;
import org.epam.bursethread.entity.broker.Broker;
import org.epam.bursethread.entity.stock.Stock;
import org.epam.bursethread.exception.TechException;
import org.epam.bursethread.output.Sounder;

/**
 *
 * @author Sergiusz
 */
public class Burse {

    private static final Logger LOGGER = Logger.getLogger("console-file");
    private final Semaphore SEMAPHORE;

    private ConcurrentLinkedQueue<Broker> burse;
    private List<Stock> stocks;
    private Sounder sounder;
    private double index;

    public Burse(Semaphore semaph) {
        burse = new ConcurrentLinkedQueue<>();
        stocks = new StocksData().getStocks();
        sounder = new Sounder();
        this.SEMAPHORE = semaph;
        index = 103;
    }

    public Sounder getSounder() {
        return sounder;
    }

    public Stock getStock(String type) {
        for (Stock stock : stocks) {
            if (stock.getNAME().equals(type)) {
                return stock;
            }
        }
        return null;
    }

    public void startAddingThread() {
        new Thread(new AddingBrokerThread()).start();
    }

    public void startTradingThread() {
        new Thread(new TradingThread()).start();
    }

    private class AddingBrokerThread implements Runnable {

        private Set<Broker> traders;

        public AddingBrokerThread() {
            traders = new TradersData().getTraders();
        }

        @Override
        public void run() {
            try {
                for (Broker trader : traders) {
                    while (SEMAPHORE.tryAcquire() != true) {
                        Thread.sleep(30);
                    }
                    burse.add(trader);
                    sounder.appendTrader(trader);
                    index += 1;
                    SEMAPHORE.release();
                    Thread.sleep(25);
                }
            } catch (InterruptedException ex) {
                LOGGER.error(new TechException("Interrupted ex in adding", ex));
            }
        }
    }

    private class TradingThread implements Runnable {

        private TradingThread() {
        }

        @Override
        public void run() {
            try {
                int startSize = 0;
                if (burse.isEmpty()) {
                    Thread.sleep(70);
                }
                while (startSize != burse.size()) {
                    startSize = burse.size();
                    for (Broker firstTrader : burse) {
                        while (SEMAPHORE.tryAcquire() != true) {
                            Thread.sleep(25);
                        }
                        for (Broker secondTrader : burse) {
                            if (firstTrader == secondTrader
                                    || index < 90
                                    || burse.isEmpty()) {
                                Thread.sleep(8);
                            } else {
                                if (firstTrader.getMISSION()
                                        .matchMissions(secondTrader.getMISSION(),
                                                getStock(firstTrader.getMISSION().getStockType()))) {
                                    sounder.appendDeal(firstTrader, secondTrader, getStock(firstTrader.getMISSION().getStockType()));
                                    if (!checkTradingClause(firstTrader, secondTrader)) {
                                        break;
                                    }
                                }
                            }
                        }
                        SEMAPHORE.release();
                        Thread.sleep(20);
                    }
                }
                sounder.appendTradeResult(stocks);
                Main.callSounder();
            } catch (InterruptedException ex) {
                LOGGER.error(new TechException("Interrupted ex in matching.", ex));
            }
        }

        private boolean checkTradingClause(Broker firstTrader, Broker secondTrader) {
            boolean answer = true;
            if (firstTrader.getMISSION().getStockNum() == 0) {
                if (!burse.remove(firstTrader)) {
                    answer = false;
                }
                sounder.removeTrader(firstTrader);
                index -= 4;
            }
            if (secondTrader.getMISSION().getStockNum() == 0) {
                if (!burse.remove(secondTrader)) {
                    answer = false;
                }
                sounder.removeTrader(secondTrader);
                index -= 4;
            }
            return answer;
        }
    }
}
