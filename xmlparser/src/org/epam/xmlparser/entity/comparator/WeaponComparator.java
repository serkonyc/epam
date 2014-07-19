/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.xmlparser.entity.comparator;

import java.util.Comparator;
import org.epam.xmlparser.entity.AbstractWeapon;

/**
 *
 * @author Sergiusz
 */
public class WeaponComparator implements Comparator<AbstractWeapon> {

    @Override
    public int compare(AbstractWeapon first_weapon, AbstractWeapon second_weapon) {
        if (!(first_weapon == null || second_weapon == null)) {
            int value = (first_weapon.getRate() - second_weapon.getRate());
            return value == 0 ? 0 : value < 0 ? -1 : 1;
        } else {
            return 0;
        }
    }
}
