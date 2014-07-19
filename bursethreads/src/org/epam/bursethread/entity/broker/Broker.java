/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.bursethread.entity.broker;

import java.util.Objects;
import org.epam.bursethread.entity.stock.Stock;

/**
 *
 * @author Sergiusz
 */
public class Broker {

    public class Mission {

        private double maxCurrPrice;
        private double minCurrPrice;
        private String actionType;
        private String stockType;
        private int stockNum;

        public Mission(double maxCurrPrice, double minCurrPrice, String actionType, String stockType, int stockNum) {
            this.maxCurrPrice = maxCurrPrice;
            this.minCurrPrice = minCurrPrice;
            this.actionType = actionType;
            this.stockType = stockType;
            this.stockNum = stockNum;
        }

        public double getMaxCurrPrice() {
            return maxCurrPrice;
        }

        public double getMinCurrPrice() {
            return minCurrPrice;
        }

        public String getActionType() {
            return actionType;
        }

        public String getStockType() {
            return stockType;
        }

        public int getStockNum() {
            return stockNum;
        }

        public void reduceStockNum(int number) {
            stockNum -= number;
        }

        public boolean matchMissions(Mission mission, Stock currStock) {
            if (this.getActionType().equals(mission.getActionType())
                    || !stockType.equals(mission.getStockType())
                    || this.getMaxCurrPrice() < mission.getMinCurrPrice()
                    || this.getMinCurrPrice() > mission.getMaxCurrPrice()
                    || mission.getStockNum() > currStock.getNumber()
                    || this.getStockNum() > currStock.getNumber()) {
                return false;
            } else {
                if (mission.getStockNum() >= this.getStockNum() && this.getStockNum() != 0) {
                    currStock.wasTraded((this.getMaxCurrPrice() + mission.getMinCurrPrice()) / 2, this.getStockNum());
                    mission.reduceStockNum(this.getStockNum());
                    this.reduceStockNum(this.getStockNum());
                } else if (mission.getStockNum() != 0) {
                    currStock.wasTraded((this.getMinCurrPrice() + mission.getMaxCurrPrice()) / 2, mission.getStockNum());
                    this.reduceStockNum(mission.getStockNum());
                    mission.reduceStockNum(mission.getStockNum());
                }
                return true;
            }
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 17 * hash + Objects.hashCode(this.actionType);
            hash = 16 * hash + Objects.hashCode(this.stockType);
            hash = 15 * hash + this.stockNum;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Mission other = (Mission) obj;
            if (!Objects.equals(this.actionType, other.actionType)) {
                return false;
            }
            if (!Objects.equals(this.stockType, other.stockType)) {
                return false;
            }
            if (this.stockNum != other.stockNum) {
                return false;
            }
            return true;
        }

    }

    private final String NAME;
    private final Mission MISSION;

    public Broker(String name, double maxCurrPrice, double minCurrPrice, String actionType, String stockType, int stockNum) {
        this.NAME = name;
        this.MISSION = new Mission(maxCurrPrice, minCurrPrice, actionType, stockType, stockNum);
    }

    public String getNAME() {
        return NAME;
    }

    public Mission getMISSION() {
        return MISSION;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 15 * hash + Objects.hashCode(this.MISSION);
        hash = 14 * hash + Objects.hashCode(this.NAME);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Broker other = (Broker) obj;
        if (!Objects.equals(this.MISSION, other.MISSION)) {
            return false;
        }
        if (!Objects.equals(this.NAME, other.NAME)) {
            return false;
        }
        return true;
    }

}
