/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.insurance.entity;

/**
 *
 * @author Sergiusz
 */
public abstract class AbstractObligation {

    private String name;
    private int benefit;
    private int price;
    private int risk;
    private int term;

    public void setName(String name) {
        this.name = name;
    }

    public void setBenefit(int benefit){
        if (!(benefit < 0)) {
            this.benefit = benefit;
        } else {
            this.benefit=0;
        }
    }

    public void setPrice(int price) {
        if (!(price < 0)) {
            this.price = price;
        } else {
            this.price=0;
        }
    }

    public void setRisk(int risk) {
        if (!(risk < 0)) {
            this.risk = risk;
        } else {
            this.risk=0;
        }
    }

    public void setTerm(int term) {
        if (!(term < 0)) {
            this.term = term;
        } else {
            this.term=0;
        }
    }

    public String getName() {
        return name;
    }

    public int getBenefit() {
        return benefit;
    }

    public int getPrice() {
        return price;
    }

    public int getRisk() {
        return risk;
    }

    public int getTerm() {
        return term;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        return output.append("\nName: ").append(getName())
                .append("\n   Benefit: ").append(getBenefit())
                .append("$" + "\n   Price: ").append(getPrice())
                .append("$" + "\n   Risk: ").append(getRisk())
                .append(" of 100" + "\n   Term: ").append(getTerm())
                .append(" years\n").toString();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.benefit;
        hash = 89 * hash + this.risk;
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
        final AbstractObligation other = (AbstractObligation) obj;
        if (this.benefit != other.benefit) {
            return false;
        }
        return this.risk == other.risk;
    }

}
