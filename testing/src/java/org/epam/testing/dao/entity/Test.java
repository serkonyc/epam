/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.dao.entity;

/**
 * Сущность теста.
 *
 * @author Sergiusz
 */
public class Test extends AbstractEntity {

    private int themeId;
    private int tutorId;
    private Theme theme;

    /**
     * Создание теста.
     *
     * @param theme Тема, к которой относится тест.
     * @param args Аргументы создания: id, themeId, tutorId.
     */
    public Test(Theme theme, int... args) {
        super(args[0]);
        this.themeId = args[1];
        this.tutorId = args[2];
        this.theme = theme;
    }

    /**
     * Создание пустого теста.
     *
     * @param id Идентификатор (0)
     */
    public Test(int id) {
        super(id);
    }

    /**
     * Получение идентификатора темы, которой принадлежит тест.
     *
     * @return ID темы
     */
    public int getThemeId() {
        return themeId;
    }

    /**
     * Получение идентификатора создавшего тест пользователя.
     *
     * @return ID пользователя
     */
    public int getTutorId() {
        return tutorId;
    }

    /**
     * Получение объекта темы, которой принадлежит тест.
     *
     * @return Объект темы
     */
    public Theme getTheme() {
        return theme;
    }

}
