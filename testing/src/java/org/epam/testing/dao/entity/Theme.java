/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.dao.entity;

/**
 * Сущность темы.
 *
 * @author Sergiusz
 */
public class Theme extends AbstractEntity implements Comparable<Theme> {

    private String name;
    private Subject subj;

    /**
     * Конструктор создания темы.
     *
     * @param subj Объект предмета, которому принадлежит тема
     * @param id Идентификатор сущности
     * @param name Название темы
     */
    public Theme(Subject subj, int id, String name) {
        super(id);
        this.name = name;
        this.subj = subj;
    }

    /**
     * Получение названия темы.
     *
     * @return Название темы
     */
    public String getName() {
        return name;
    }

    /**
     * Получение объекта предмета.
     *
     * @return Предмет, которому принадлежит тема.
     */
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
