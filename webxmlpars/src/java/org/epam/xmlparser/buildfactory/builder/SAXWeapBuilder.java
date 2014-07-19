/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.xmlparser.buildfactory.builder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.epam.xmlparser.buildfactory.additproc.SAXProc;
import org.epam.xmlparser.entity.AbstractWeapon;
import org.epam.xmlparser.exception.TechException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author Sergiusz
 */
public class SAXWeapBuilder extends AbstractWeapBuilder {

    public SAXWeapBuilder() {
    }

    public SAXWeapBuilder(ArrayList<AbstractWeapon> weapons) {
        super(weapons);
    }

    @Override
    public void buildWeaponArrays(String fileName) throws TechException {
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            SAXProc xml_inp = new SAXProc();
            reader.setContentHandler(xml_inp);
            reader.parse(fileName);
            weapons = xml_inp.getWeaponList();
        } catch (SAXException e) {
            throw new TechException(" SAX.", e);
        } catch (IOException e) {
            throw new TechException(".", e);
        }
    }

}
