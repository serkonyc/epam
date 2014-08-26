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
public class Theme extends AbstractEntity implements Comparable<Theme> {

    private int subjectId;
    private String name;
    private Subject subj;

    public Theme(Subject subj, int id, int subjectId, String name) {
        super(id);
        this.subjectId = subjectId;
        this.name = name;
        this.subj = subj;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public String getName() {
        return name;
    }

    public Subject getSubj() {
        return subj;
    }

    @Override
    public int compareTo(Theme o) {
        if (!(this == null || o == null)) {
            boolean value = (this.getName().equals(o.getName()));
            return value == false ? 1 : 0;
        } else {
            return 0;
        }
    }
}
