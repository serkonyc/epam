/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.insurance.entity;

import java.util.Objects;

/**
 *
 * @author Sergiusz
 */
public class PropObligation extends AbstractObligation {

    private String kindOfProp = "";

    /*public PropObligation(String name, int benefit, int price, int risk, int term, String kindOfProp) {
     super(name, benefit, price, risk, term);
     this.kindOfProp = kindOfProp;
     }*/
    public void setKindOfProp(String kindOfProp) {
        this.kindOfProp = kindOfProp;
    }

    public String getKindOfProp() {
        return kindOfProp;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(super.toString());
        return output.append("   Kind of property ").append(getKindOfProp())
                .append("\n").toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.kindOfProp);
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
        final PropObligation other = (PropObligation) obj;
        return Objects.equals(this.kindOfProp, other.kindOfProp);
    }

}
