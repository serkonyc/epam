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
public class HarmObligation extends AbstractObligation {

    private int harm_by_ISS;

    /*public HarmObligation(String name, int benefit, int price, int risk, int term, int harm_by_ISS) {
     super(name, benefit, price, risk, term);
     this.harm_by_ISS = harm_by_ISS;
     }*/
    public void setHarm_by_ISS(int harm_by_ISS) {
        if (!(harm_by_ISS < 0)) {
            this.harm_by_ISS = harm_by_ISS;
        } else {
            this.harm_by_ISS=0;
        }
    }

    public int getHarm_by_ISS() {
        return harm_by_ISS;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(super.toString());
        return output.append("   Harm by ISS (0-75): ")
                .append(getHarm_by_ISS()).append("\n").toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.harm_by_ISS;
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
        final HarmObligation other = (HarmObligation) obj;
        return this.harm_by_ISS == other.harm_by_ISS;
    }

}
