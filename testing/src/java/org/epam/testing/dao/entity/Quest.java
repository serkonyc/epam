/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.dao.entity;

import java.util.ArrayList;

/**
 * Сущность вопроса.
 *
 * @author Sergiusz
 */
public class Quest extends AbstractEntity {

    private int testId;
    private String text;
    private ArrayList<Answer> answers;

    /**
     * Конструктор создания вопроса.
     *
     * @param id Идентификатор сущности
     * @param testId Идентификатор теста-владельца
     * @param text Текстовое содержимое
     * @param args Все альтернативы ответов на вопрос (заполненные объекты
     * Answer)
     */
    public Quest(int id, int testId, String text, Answer... args) {
        super(id);
        this.testId = testId;
        this.text = text;
        answers = new ArrayList<>(5);
        for (Answer arg : args) {
            answers.add(arg);
        }
    }

    /**
     * Получение идентификатора теста-владельца.
     *
     * @return ID теста
     */
    public int getTestId() {
        return testId;
    }

    /**
     * Получение текстового содержимого вопроса.
     *
     * @return Текст вопроса
     */
    public String getText() {
        return text;
    }

    /**
     * Получение списка со всеми ответами для данного вопроса.
     *
     * @return Список ответов.
     */
    public ArrayList<Answer> getAnswers() {
        return answers;
    }
}
