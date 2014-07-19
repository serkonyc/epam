/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.insurance.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.epam.insurance.entity.AbstractObligation;
import org.epam.insurance.entity.comparator.ObligationComparator;
import org.epam.insurance.xmlproc.XMLProc;

/**
 *
 * @author Sergiusz
 */
public class CollectObligations {

    List<AbstractObligation> deriv;
    List<AbstractObligation> gen_deriv;
    double all_benef, all_cost;

    CollectObligations(XMLProc sh) {
        deriv = sh.getDerivative();
    }

    public String makePack() {
        Collections.sort(deriv, new ObligationComparator());
        gen_deriv = new ArrayList<>();
        Random random = new Random();
        for (AbstractObligation deriv1 : deriv) {
            int res = random.nextInt(2);
            AbstractObligation curr_oblig = deriv1;
            boolean dont_add = false;
            if (!gen_deriv.isEmpty()) {
                for (AbstractObligation smstr1 : gen_deriv) {
                    if (smstr1.getClass() == curr_oblig.getClass()) {
                        dont_add = true;
                    }
                }
            }
            if (dont_add == false && res == 0) {
                gen_deriv.add(curr_oblig);
                all_benef += (curr_oblig.getBenefit());
                all_cost += (curr_oblig.getPrice());
            }
        }
        return this.toString();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        return (output.append("\n\nDerivative:\n\n")
                .append(gen_deriv).append("\n\n")
                .append("All cost is ").append(all_cost)
                .append(", all benefit is ").append(all_benef).toString());
    }
}
