/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.dao.entity;

/**
 * Сущность предмета.
 *
 * @author Sergiusz
 */
public class Subject extends AbstractEntity implements Comparable<Subject> {

    private String name;

    /**
     * Конструктор создания предмета.
     *
     * @param id Идентификатор сущности
     * @param name Название предмета
     */
    public Subject(int id, String name) {
        super(id);
        this.name = name;
    }

    /**
     * Получение названия предмета.
     *
     * @return Название
     */
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
