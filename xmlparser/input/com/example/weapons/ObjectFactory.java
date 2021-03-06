//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.06 at 11:31:08 PM FET 
//


package com.example.weapons;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.example.weapons package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Gun_QNAME = new QName("http://www.example.com/weapons", "gun");
    private final static QName _Equip_QNAME = new QName("http://www.example.com/weapons", "equip");
    private final static QName _Weapon_QNAME = new QName("http://www.example.com/weapons", "weapon");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.example.weapons
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Equip }
     * 
     */
    public Equip createEquip() {
        return new Equip();
    }

    /**
     * Create an instance of {@link Weapon }
     * 
     */
    public Weapon createWeapon() {
        return new Weapon();
    }

    /**
     * Create an instance of {@link Weapons }
     * 
     */
    public Weapons createWeapons() {
        return new Weapons();
    }

    /**
     * Create an instance of {@link Gun }
     * 
     */
    public Gun createGun() {
        return new Gun();
    }

    /**
     * Create an instance of {@link Mass }
     * 
     */
    public Mass createMass() {
        return new Mass();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Gun }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.com/weapons", name = "gun", substitutionHeadNamespace = "http://www.example.com/weapons", substitutionHeadName = "weapon")
    public JAXBElement<Gun> createGun(Gun value) {
        return new JAXBElement<Gun>(_Gun_QNAME, Gun.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Equip }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.com/weapons", name = "equip", substitutionHeadNamespace = "http://www.example.com/weapons", substitutionHeadName = "weapon")
    public JAXBElement<Equip> createEquip(Equip value) {
        return new JAXBElement<Equip>(_Equip_QNAME, Equip.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Weapon }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.com/weapons", name = "weapon")
    public JAXBElement<Weapon> createWeapon(Weapon value) {
        return new JAXBElement<Weapon>(_Weapon_QNAME, Weapon.class, null, value);
    }

}
