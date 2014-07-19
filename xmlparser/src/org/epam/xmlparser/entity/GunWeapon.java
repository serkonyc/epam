/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.xmlparser.entity;

import java.util.Objects;

/**
 *
 * @author Sergiusz
 */
public class GunWeapon extends AbstractWeapon {

    public class Mass {

        private double complected;
        private double uncomplected;

        public double getComplected() {
            return complected;
        }

        public void setComplected(double complected) {
            this.complected = complected;
        }

        public double getUncomplected() {
            return uncomplected;
        }

        public void setUncomplected(double uncomplected) {
            this.uncomplected = uncomplected;
        }
    }

    private Mass mass = new Mass();

    public Mass getMass() {
        return mass;
    }

    public void setMass(Mass mass) {
        this.mass = mass;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(super.toString());
        return output.append("\n   mass uncomplected: ").append(mass.getUncomplected()).append("\n").
                append("    mass complected: ").append(mass.getComplected()).toString();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.getType());
        hash = 37 * hash + Objects.hashCode(this.mass);
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
        final GunWeapon other = (GunWeapon) obj;
        if (!Objects.equals(this.getType(), other.getType())) {
            return false;
        }
        return Objects.equals(this.mass, other.mass);
    }

}
