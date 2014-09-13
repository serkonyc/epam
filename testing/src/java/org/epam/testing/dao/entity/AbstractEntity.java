/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.dao.entity;

/**
 * Абстрактная сущность.
 *
 * @author Sergiusz
 */
public abstract class AbstractEntity {

    private int id;

    /**
     * Конструктор с одним числовым параметром.
     *
     * @param id Идентификатор записи из БД.
     */
    protected AbstractEntity(int id) {
        this.id = id;
    }

    /**
     * Получение id сущности.
     *
     * @return Идентификатор сущности
     */
    public int getId() {
        return this.id;
    }
}
