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
public class OtherObligation extends AbstractObligation {

    private String otherType = "";

    /*public PropObligation(String name, int benefit, int price, int risk, int term, String kindOfProp) {
     super(name, benefit, price, risk, term);
     this.kindOfProp = kindOfProp;
     }*/
    public void setOtherType(String otherType) {
        this.otherType = otherType;
    }

    public String getOtherType() {
        return otherType;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(super.toString());
        return output.append("   Other type ").append(getOtherType())
                .append("\n").toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.otherType);
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
        final OtherObligation other = (OtherObligation) obj;
        return Objects.equals(this.otherType, other.otherType);
    }

}
