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
public abstract class AbstractEntity {

    private int id;

    protected AbstractEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
