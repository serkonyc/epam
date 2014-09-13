/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.dao.entity;

/**
 * Сущность ответа.
 *
 * @author Sergiusz
 */
public class Answer extends AbstractEntity {

    private int questId;
    private boolean right;
    private String text;
    private String textAdv;
    private boolean ifCheckBox;

    /**
     * Конструктор создания ответа.
     *
     * @param id Идентификатор сущности из БД.
     * @param questId Идентификатор вопроса, которому принадлежит данный ответ.
     * @param right Правильность (true or false)
     * @param text Текстовое содержимое ответа.
     */
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

    /**
     * Получение идентификатора вопроса-хозяина.
     *
     * @return Идентификатор вопроса
     */
    public int getQuestId() {
        return questId;
    }

    /**
     * Получение данных о правильности ответа.
     *
     * @return true or false
     */
    public boolean isRight() {
        return right;
    }

    /**
     * Получение текстового содержимого.
     *
     * @return Текст ответа
     */
    public String getText() {
        return text;
    }

    /**
     * Получение текста второго варианта ответа в случае наличия нескольких
     * правильных ответов
     *
     * @return Текст ответа
     */
    public String getTextAdv() {
        return textAdv;
    }

    /**
     * Получение данных о необходимости использования множественного выбора
     * ответа.
     *
     * @return true or false
     */
    public boolean isIfCheckBox() {
        return ifCheckBox;
    }

}
