/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.xmlparser.weapfactory;

import org.epam.xmlparser.entity.AbstractWeapon;
import org.epam.xmlparser.entity.EquipWeapon;
import org.epam.xmlparser.entity.GunWeapon;

/**
 *
 * @author Sergiusz
 */
public class WeaponFactory {

    private enum Attributes {

        GUN, EQUIP
    }

    public AbstractWeapon getInheritWeapon(String enumAttr) {
        Attributes currentEnum = Attributes.valueOf(enumAttr.toUpperCase());
        switch (currentEnum) {
            case GUN:
                return new GunWeapon();
            default:
                return new EquipWeapon();
        }
    }

}
