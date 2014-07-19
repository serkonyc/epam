/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.insurance.control;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.epam.insurance.xmlproc.XMLProc;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author Sergiusz
 */
public class InsuranceMaker {

    static final Logger LOGGER = Logger.getLogger("console-file");

    public static void main(String[] args) {
        DOMConfigurator.configure("log4j.xml");
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            XMLProc xml_inp = new XMLProc();
            reader.setContentHandler(xml_inp);
            reader.parse("xml_input.xml");
            System.out.print(xml_inp.toString());
            CollectObligations gen_deriv = new CollectObligations(xml_inp);
            System.out.println(gen_deriv.makePack());
        } catch (SAXException | IOException | NullPointerException e) {
            LOGGER.error(e);
        }
    }
}
