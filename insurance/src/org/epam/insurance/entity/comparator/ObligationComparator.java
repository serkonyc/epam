/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.insurance.entity.comparator;

import java.util.Comparator;
import org.epam.insurance.entity.AbstractObligation;

/**
 *
 * @author Sergiusz
 */
public class ObligationComparator implements Comparator<AbstractObligation> {

    @Override
    public int compare(AbstractObligation first_oblig, AbstractObligation second_oblig) {
        if (!(first_oblig == null || second_oblig == null)) {
            int value = (first_oblig.getRisk() - second_oblig.getRisk());
            return value == 0 ? 0 : value < 0 ? -1 : 1;
        } else {
            return 0;
        }
    }
}
