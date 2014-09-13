/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.dao.entity;

/**
 * Сущность истории прохождения.
 *
 * @author Sergiusz
 */
public class PassHistory extends AbstractEntity {

    private int testId;
    private int userId;
    private int result;

    /**
     * Конструктор создания записи истории прохождения.
     *
     * @param result Результат прохождения в долях.
     * @param args Аргументы: id, testId, userId.
     */
    public PassHistory(float result, int... args) {
        super(args[0]);
        this.testId = args[1];
        this.userId = args[2];
        this.result = (int) result;
    }

    /**
     * Конструктор создания пустой записи истории.
     *
     * @param id Идентификатор (0)
     */
    public PassHistory(int id) {
        super(id);
    }

    /**
     * Получение идентификатора пройденного теста.
     *
     * @return ID теста
     */
    public int getTestId() {
        return testId;
    }

    /**
     * Получение идентификатора прошедшего тест пользователя.
     *
     * @return ID пользователя
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Получение результата прохождения теста
     *
     * @return Результат прохождения (0-100)
     */
    public int getResult() {
        return result;
    }
}
