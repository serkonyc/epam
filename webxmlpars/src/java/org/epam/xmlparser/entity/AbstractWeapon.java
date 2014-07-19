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
public abstract class AbstractWeapon {

    private String name;
    private String country;
    private int rate;
    private String id;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        return output.append("\nName: ").append(getName())
                .append("\n   Country: ").append(getCountry())
                .append("\n   Rate: ").append(getRate())
                .append("m" + "\n   ID: ").append(getId())
                .append("\n   Type: ").append(getType()).toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.name);
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
        final AbstractWeapon other = (AbstractWeapon) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        if (this.rate != other.rate) {
            return false;
        }
        return this.id == other.id;
    }

}
