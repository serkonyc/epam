/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.dao.factory;

/**
 * Перечисление типов Dao.
 *
 * @author Sergiusz
 */
public enum DaoType {

    /**
     * Доступ к управлению сущностями пользователя.
     */
    USER,
    /**
     * Доступ к управлению сущностями предмета.
     */
    SUBJECT,
    /**
     * Доступ к управлению сущностями темы.
     */
    THEME,
    /**
     * Доступ к управлению сущностями теста.
     */
    TEST,
    /**
     * Доступ к управлению сущностями вопроса.
     */
    QUEST,
    /**
     * Доступ к управлению сущностями ответа.
     */
    ANSWER,
    /**
     * Доступ к управлению сущностями истории.
     */
    PASSHISTORY
}
