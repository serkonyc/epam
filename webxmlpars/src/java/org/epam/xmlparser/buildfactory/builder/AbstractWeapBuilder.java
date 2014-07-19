/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.xmlparser.buildfactory.builder;

import java.util.ArrayList;
import org.epam.xmlparser.entity.AbstractWeapon;
import org.epam.xmlparser.exception.TechException;

/**
 *
 * @author Sergiusz
 */
public abstract class AbstractWeapBuilder {

    protected ArrayList<AbstractWeapon> weapons;

    public AbstractWeapBuilder() {
        weapons = new ArrayList<>();
    }

    public AbstractWeapBuilder(ArrayList<AbstractWeapon> weapons) {
        this.weapons = weapons;
    }

    public ArrayList<AbstractWeapon> getWeapons() {
        return weapons;
    }

    abstract public void buildWeaponArrays(String fileName) throws TechException;
}
