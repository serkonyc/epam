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
public class Test extends AbstractEntity {

    private int themeId;
    private int tutorId;
    private Theme theme;

    public Test(Theme theme, int... args) {
        super(args[0]);
        this.themeId = args[1];
        this.tutorId = args[2];
        this.theme = theme;
    }

    public int getThemeId() {
        return themeId;
    }

    public int getTutorId() {
        return tutorId;
    }

    public Theme getTheme() {
        return theme;
    }

}
