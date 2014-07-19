/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.insurance.factory;

import org.epam.insurance.entity.AbstractObligation;
import org.epam.insurance.entity.HarmObligation;
import org.epam.insurance.entity.OtherObligation;
import org.epam.insurance.entity.PropObligation;
import org.epam.insurance.entity.RespObligation;

//(почему бы не использовать import org.epam.insurance.entities.*; ?)
/**
 *
 * @author Sergiusz
 */
public class ObligationFactory {

    private enum Attributes {

        PROPERTY, HARM, RESPONSIBILITY, NEW
    }

    public AbstractObligation getObligation(String enumAttr) {
        Attributes currentEnum = Attributes.valueOf(enumAttr.toUpperCase());
        switch (currentEnum) {
            case PROPERTY:
                return new PropObligation();
            case HARM:
                return new HarmObligation();
            case RESPONSIBILITY:
                return new RespObligation();
            default:
                return new OtherObligation();
        }
    }

}
