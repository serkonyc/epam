/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.xmlparser.buildfactory.builder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.epam.xmlparser.entity.AbstractWeapon;
import org.epam.xmlparser.entity.EquipWeapon;
import org.epam.xmlparser.entity.GunWeapon;
import org.epam.xmlparser.exception.TechException;

/**
 *
 * @author Sergiusz
 */
public class StAXWeapBuilder extends AbstractWeapBuilder {

    private XMLInputFactory inputFactory;

    public StAXWeapBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    public StAXWeapBuilder(ArrayList<AbstractWeapon> weapons) {
        super(weapons);
    }

    @Override
    public void buildWeaponArrays(String fileName) throws TechException {
        FileInputStream inputStream = null;
        XMLStreamReader reader;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (WeapEnum.valueOf(name.toUpperCase()) == WeapEnum.GUN
                            | WeapEnum.valueOf(name.toUpperCase()) == WeapEnum.EQUIP) {
                        AbstractWeapon weapon = buildWeapon(reader);
                        weapons.add(weapon);
                    }
                }
            }
        } catch (XMLStreamException ex) {
            throw new TechException("StAX: Stram trouble.", ex);
        } catch (FileNotFoundException ex) {
            throw new TechException("StAX: File wasn't found.", ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                throw new TechException("Input-output exception.", ex);
            }
        }
    }

    private AbstractWeapon buildWeapon(XMLStreamReader reader) throws XMLStreamException, TechException {
        AbstractWeapon weapon = null;
        String[] values = new String[5];
        String name;
        values[0] = (reader.getAttributeValue(null, WeapEnum.ID.getValue()));
        values[1] = (reader.getAttributeValue(null, WeapEnum.TYPE.getValue()));
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (WeapEnum.valueOf(name.toUpperCase())) {
                        case COMPLECTED:
                            weapon = new GunWeapon();
                            weapon.setId(values[0]);
                            weapon.setType(values[1]);
                            weapon.setName(values[2]);
                            weapon.setCountry(values[3]);
                            weapon.setRate(Integer.parseInt(values[4]));
                            ((GunWeapon) weapon).getMass().setComplected(Double.parseDouble(getXMLText(reader)));
                            break;
                        case UNCOMPLECTED:
                            if (weapon != null) {
                                ((GunWeapon) weapon).getMass().setUncomplected(Double.parseDouble(getXMLText(reader)));
                            }
                            break;
                        case MASS:
                            weapon = new EquipWeapon();
                            weapon.setId(values[0]);
                            weapon.setType(values[1]);
                            weapon.setName(values[2]);
                            weapon.setCountry(values[3]);
                            weapon.setRate(Integer.parseInt(values[4]));
                            ((EquipWeapon) weapon).setMass(Double.parseDouble(getXMLText(reader)));
                            break;
                        case ARCGRAD:
                            if (weapon != null) {
                                ((EquipWeapon) weapon).setArcgrad(Integer.parseInt(getXMLText(reader)));
                            }
                            break;
                        case NAME:
                            values[2] = (getXMLText(reader));
                            break;
                        case COUNTRY:
                            values[3] = getXMLText(reader);
                            break;
                        case RATE:
                            values[4] = getXMLText(reader);
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (WeapEnum.valueOf(name.toUpperCase()) == WeapEnum.GUN
                            | WeapEnum.valueOf(name.toUpperCase()) == WeapEnum.EQUIP) {
                        return weapon;
                    }
                    break;
            }
        }
        throw new TechException("StAX: buildWeapon exception!");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
