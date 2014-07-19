/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.xmlparser.buildfactory.builder;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.epam.xmlparser.entity.AbstractWeapon;
import org.epam.xmlparser.entity.EquipWeapon;
import org.epam.xmlparser.entity.GunWeapon;
import org.epam.xmlparser.exception.TechException;
import org.epam.xmlparser.weapfactory.WeaponFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Sergiusz
 */
public class DOMWeapBuilder extends AbstractWeapBuilder {

    private DocumentBuilder docBuilder;

    public DOMWeapBuilder() throws TechException {
        this.weapons = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new TechException("������ ������������ �������", e);
        }
    }

    public DOMWeapBuilder(ArrayList<AbstractWeapon> weapons) {
        super(weapons);
    }

    @Override
    public void buildWeaponArrays(String fileName) throws TechException {
        Document doc;
        try {
            doc = docBuilder.parse(fileName);
            Element rootEl = doc.getDocumentElement();
            NodeList gunList = rootEl.getElementsByTagName("gun");
            NodeList equipList = rootEl.getElementsByTagName("equip");
            for (int i = 0; i < gunList.getLength(); i++) {
                Element weaponElement = (Element) gunList.item(i);
                AbstractWeapon weapon = buildWeapon(weaponElement);
                weapons.add(weapon);
            }
            for (int i = 0; i < equipList.getLength(); i++) {
                Element weaponElement = (Element) equipList.item(i);
                AbstractWeapon weapon = buildWeapon(weaponElement);
                weapons.add(weapon);
            }
        } catch (IOException e) {
            throw new TechException("�������� ������ �����-������.", e);
        } catch (SAXException e) {
            throw new TechException("�������� ����������� parse.", e);
        }
    }

    private AbstractWeapon buildWeapon(Element weaponElement) {
        WeaponFactory weapFac = new WeaponFactory();
        AbstractWeapon weapon = weapFac.getInheritWeapon(weaponElement.getTagName());

        weapon.setId(weaponElement.getAttribute("id"));
        weapon.setType(weaponElement.getAttribute("type"));
        weapon.setName(getElementTextContent(weaponElement, "name"));
        weapon.setCountry(getElementTextContent(weaponElement, "country"));
        weapon.setRate(Integer.parseInt(getElementTextContent(weaponElement, "rate")));
        switch (weaponElement.getTagName()) {
            case "gun":
                ((GunWeapon) weapon).getMass().setComplected(Double.parseDouble(getElementTextContent(weaponElement, "complected")));
                ((GunWeapon) weapon).getMass().setUncomplected(Double.parseDouble(getElementTextContent(weaponElement, "uncomplected")));
                break;
            case "equip":
                ((EquipWeapon) weapon).setArcgrad(Integer.parseInt(getElementTextContent(weaponElement, "arcgrad")));
                ((EquipWeapon) weapon).setMass(Double.parseDouble(getElementTextContent(weaponElement, "mass")));
                break;
        }
        return weapon;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }

}
