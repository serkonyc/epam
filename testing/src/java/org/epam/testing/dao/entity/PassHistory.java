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
public class PassHistory extends AbstractEntity {

    private int testId;
    private int userId;
    private int result;

    public PassHistory(float result, int... args) {
        super(args[0]);
        this.testId = args[1];
        this.userId = args[2];
        this.result = (int) result;
    }

    public PassHistory(int id) {
        super(id);
    }

    public int getTestId() {
        return testId;
    }

    public int getUserId() {
        return userId;
    }

    public int getResult() {
        return result;
    }
}
