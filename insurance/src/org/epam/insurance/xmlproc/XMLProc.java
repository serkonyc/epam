/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.insurance.xmlproc;

import java.util.ArrayList;
import java.util.List;
import org.epam.insurance.entity.AbstractObligation;
import org.epam.insurance.entity.HarmObligation;
import org.epam.insurance.entity.OtherObligation;
import org.epam.insurance.entity.PropObligation;
import org.epam.insurance.entity.RespObligation;
import org.epam.insurance.factory.ObligationFactory;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 *
 * @author Sergiusz
 */
public class XMLProc implements ContentHandler {

    private enum ObligEnum {

        INSURANCE, OBLIGATION, NAME, BENEFIT, PRICE, RISK, TERM, KIND, ISS, GAMMA, OTHERTYPE

    }

    ArrayList<AbstractObligation> deriv = new ArrayList<>();
    AbstractObligation curr = null;
    ObligEnum currentEnum = null;

    public List<AbstractObligation> getDerivative() {
        return deriv;
    }

    @Override
    public void startDocument() {
    }

    @Override
    public void startElement(String uri, String localName,
            String qName, Attributes attrs) {
        ObligationFactory oblifac = new ObligationFactory();
        if (qName.equals("obligation")) {
            curr = oblifac.getObligation(attrs.getValue(0));
        }
        currentEnum = ObligEnum.valueOf(qName.toUpperCase());
    }

    @Override
    public void endElement(String uri, String localName,
            String qName) {
        if (qName.equals("obligation")) {
            deriv.add(curr);
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
            case BENEFIT:
                curr.setBenefit(Integer.parseInt(s));
                break;
            case PRICE:
                curr.setPrice(Integer.parseInt(s));
                break;
            case RISK:
                curr.setRisk(Integer.parseInt(s));
                break;
            case TERM:
                curr.setTerm(Integer.parseInt(s));
                break;
            case ISS:
                ((HarmObligation) curr).setHarm_by_ISS(Integer.parseInt(s));
                break;
            case GAMMA:
                ((RespObligation) curr).setGamma_coef(Double.parseDouble(s));
                break;
            case KIND:
                ((PropObligation) curr).setKindOfProp(s);
                break;
            case OTHERTYPE:
                ((OtherObligation) curr).setOtherType(s);
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
        return (output.append("\n\nAll the obligations:\n\n").append(deriv).append("\n\n").toString());
    }
}
