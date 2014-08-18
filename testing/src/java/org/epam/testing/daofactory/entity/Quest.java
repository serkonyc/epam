/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.testing.daofactory.entity;

import java.util.ArrayList;

/**
 *
 * @author Sergiusz
 */
public class Quest extends AbstractEntity {

    private int testId;
    private String text;
    private ArrayList<Answer> answers;

    public Quest(int id, int testId, String text, Answer... args) {
        super(id);
        this.testId = testId;
        this.text = text;
        answers = new ArrayList<>(5);
        for (Answer arg : args) {
            answers.add(arg);
        }
    }

    public int getTestId() {
        return testId;
    }

    public String getText() {
        return text;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }
}
