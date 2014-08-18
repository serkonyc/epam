/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.testing.daofactory.entity;

/**
 *
 * @author Sergiusz
 */
public class PassHistory extends AbstractEntity {

    private int testId;
    private int userId;
    private float result;

    public PassHistory(float result, int... args) {
        super(args[0]);
        this.testId = args[1];
        this.userId = args[2];
        this.result = result;
    }
}
