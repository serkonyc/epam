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
public class RespObligation extends AbstractObligation {

    private double gamma_coef;

    /*public RespObligation(String name, int benefit, int price, int risk, int term, double gamma_coef) {
     super(name, benefit, price, risk, term);
     this.gamma_coef = gamma_coef;
     }*/
    public void setGamma_coef(double gamma_coef) {
        if (!(gamma_coef < 0)) {
            this.gamma_coef = gamma_coef;
        } else {
            this.gamma_coef = 0;
        }
    }

    public double getGamma_coef() {
        return gamma_coef;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(super.toString());
        return output.append("   Gamma responsibility ").append(getGamma_coef())
                .append("\n").toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.gamma_coef) ^ (Double.doubleToLongBits(this.gamma_coef) >>> 32));
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
        final RespObligation other = (RespObligation) obj;
        return Double.doubleToLongBits(this.gamma_coef) == Double.doubleToLongBits(other.gamma_coef);
    }

}
