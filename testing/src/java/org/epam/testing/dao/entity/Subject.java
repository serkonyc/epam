/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.testing.dao.entity;

/**
 *
 * @author Sergiusz
 */
public class Subject extends AbstractEntity implements Comparable<Subject> {

    private String name;

    public Subject(int id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Subject o) {
        if (!(this == null || o == null)) {
            boolean value = (this.getName().equals(o.getName()));
            return value == false ? 1 : 0;
        } else {
            return 0;
        }
    }

}
