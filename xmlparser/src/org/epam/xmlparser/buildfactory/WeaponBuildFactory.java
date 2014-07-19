/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.xmlparser.buildfactory;

import org.epam.xmlparser.buildfactory.builder.AbstractWeapBuilder;
import org.epam.xmlparser.buildfactory.builder.DOMWeapBuilder;
import org.epam.xmlparser.buildfactory.builder.SAXWeapBuilder;
import org.epam.xmlparser.buildfactory.builder.StAXWeapBuilder;
import org.epam.xmlparser.exception.TechException;

/**
 *
 * @author Sergiusz
 */
public class WeaponBuildFactory {

    private enum ParseType {

        SAX, STAX, DOM
    }

    public AbstractWeapBuilder createWeaponBuilder(String typeParser) throws TechException {
        ParseType type = ParseType.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM:
                return new DOMWeapBuilder();
            case STAX:
                return new StAXWeapBuilder();
            case SAX:
                return new SAXWeapBuilder();
            default:
                throw new EnumConstantNotPresentException(
                        type.getDeclaringClass(), type.name());
        }
    }
}
