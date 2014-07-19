/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.xmlparser.buildfactory.builder;

/**
 *
 * @author Sergiusz
 */
public enum WeapEnum {

    COMPLECTED("complected"), UNCOMPLECTED("uncomplected"), MASS("mass"),
    ARCGRAD("arcgrad"), ID("id"), TYPE("type"), WEAPONS("weapons"), GUN("gun"),
    EQUIP("equip"), NAME("name"), COUNTRY("country"), RATE("rate"), COMPMASS("compmass");

    private String value;

    private WeapEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
