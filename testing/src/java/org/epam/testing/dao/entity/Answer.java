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
public class Answer extends AbstractEntity {

    private int questId;
    private boolean right;
    private String text;
    private String textAdv;
    private boolean ifCheckBox;

    public Answer(int id, int questId, boolean right, String text) {
        super(id);
        this.questId = questId;
        this.right = right;
        if (text.contains("!webtest!")) {
            this.text = text.split("!webtest!")[0];
            textAdv = text.split("!webtest!")[1];
            ifCheckBox = true;
        } else {
            this.text = text;
        }
    }

    public int getQuestId() {
        return questId;
    }

    public boolean isRight() {
        return right;
    }

    public String getText() {
        return text;
    }

    public String getTextAdv() {
        return textAdv;
    }

    public boolean isIfCheckBox() {
        return ifCheckBox;
    }

}
