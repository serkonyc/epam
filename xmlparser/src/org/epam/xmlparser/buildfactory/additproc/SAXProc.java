/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.xmlparser.buildfactory.additproc;

import java.util.ArrayList;
import org.epam.xmlparser.entity.AbstractWeapon;
import org.epam.xmlparser.entity.EquipWeapon;
import org.epam.xmlparser.entity.GunWeapon;
import org.epam.xmlparser.weapfactory.WeaponFactory;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 *
 * @author Sergiusz
 */
public class SAXProc implements ContentHandler {

    private enum WeapEnum {

        WEAPONS, GUN, EQUIP, NAME, COUNTRY, RATE, MASS, COMPLECTED, UNCOMPLECTED, ARCGRAD, COMPMASS

    }

    private ArrayList<AbstractWeapon> weapons = new ArrayList<>();
    private AbstractWeapon curr;
    private WeapEnum currentEnum;

    public ArrayList<AbstractWeapon> getWeaponList() {
        return weapons;
    }

    @Override
    public void startDocument() {
    }

    @Override
    public void startElement(String uri, String localName,
            String qName, Attributes attrs) {
        WeaponFactory weapfac = new WeaponFactory();
        if ("gun".equals(qName) | "equip".equals(qName)) {
            curr = weapfac.getInheritWeapon(qName);
            curr.setId(attrs.getValue(0));
            curr.setType(attrs.getValue(1));
        }
        currentEnum = WeapEnum.valueOf(qName.toUpperCase());
    }

    @Override
    public void endElement(String uri, String localName,
            String qName) {
        if ("gun".equals(qName) | "equip".equals(qName)) {
            weapons.add(curr);
        }
        currentEnum = null;
    }

    @Override
    public void characters(char[] ch, int start,
            int length) {
        String s = new String(ch, start, length).trim();
        if (currentEnum == null) {
            return;
        }
        switch (currentEnum) {
            case NAME:
                curr.setName(s);
                break;
            case COUNTRY:
                curr.setCountry(s);
                break;
            case RATE:
                curr.setRate(Integer.parseInt(s));
                break;
            case MASS:
                ((EquipWeapon) curr).setMass(Double.parseDouble(s));
                break;
            case COMPLECTED:
                ((GunWeapon) curr).getMass().setComplected(Double.parseDouble(s));
                break;
            case UNCOMPLECTED:
                ((GunWeapon) curr).getMass().setUncomplected(Double.parseDouble(s));
                break;
            case ARCGRAD:
                ((EquipWeapon) curr).setArcgrad(Integer.parseInt(s));
                break;
            default:
                break;
        }
    }

    @Override
    public void setDocumentLocator(Locator locator) {
    }

    @Override
    public void endDocument() throws SAXException {
    }

    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {
    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
    }

    @Override
    public void processingInstruction(String target, String data) throws SAXException {
    }

    @Override
    public void skippedEntity(String name) throws SAXException {
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        return (output.append("\n\nAll the weapons:\n\n").append(weapons).append("\n\n").toString());
    }
}
