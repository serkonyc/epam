/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.xmlparser.entity;

/**
 *
 * @author Sergiusz
 */
public class EquipWeapon extends AbstractWeapon {

    private static final String TYPE = "equip";
    private double mass;
    private int arcgrad;

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public int getArcgrad() {
        return arcgrad;
    }

    public void setArcgrad(int arcgrad) {
        this.arcgrad = arcgrad;
    }

    public String getTYPE() {
        return TYPE;
    }
    
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(super.toString());
        return output.append("\n   arcgrad: ").append(getArcgrad()).append("\n").
                append("    mass: ").append(getMass()).toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.mass) ^ (Double.doubleToLongBits(this.mass) >>> 32));
        hash = 23 * hash + this.arcgrad;
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
        final EquipWeapon other = (EquipWeapon) obj;
        if (Double.doubleToLongBits(this.mass) != Double.doubleToLongBits(other.mass)) {
            return false;
        }
        return this.arcgrad == other.arcgrad;
    }

}
