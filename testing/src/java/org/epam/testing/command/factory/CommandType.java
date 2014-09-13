/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.command.factory;

/**
 * Перечисление всех команд.
 * 
 * @author Sergiusz
 */
public enum CommandType {

    /**
     * Выход из системы, возврат в начало.
     */
    BEGIN,

    /**
     * Аутентификация.
     */
    LOGIN,

    /**
     * Регистрация.
     */
    REGISTER,

    /**
     * Требование авторизации.
     */
    NEEDLOGIN,

    /**
     * Требование регистрации.
     */
    NEEDREGISTER,

    /**
     * Подготовка теста к созданию.
     */
    PREPARETEST,

    /**
     * Выбор предмета для создания теста.
     */
    CHOOSESUBJECT,

    /**
     * Выбор темы для создания теста.
     */
    CHOOSETHEME, 

    /**
     * Окончательное создание теста.
     */
    MAKETEST, 

    /**
     * Выбор теста для прохождения.
     */
    CHOOSETEST, 

    /**
     * Завершение прохождения теста.
     */
    PASSTEST, 

    /**
     * Список тестов к изменению.
     */
    CHANGETEST,

    /**
     * Список тестов к удалению.
     */
    DELETETEST,

    /**
     * Удаление выбранного тестаю
     */
    DELETECHOSENTEST,

    /**
     * Изменение выбранного теста.
     */
    CHANGECHOSENTEST,

    /**
     * Просмотр статистики прохождений.
     */
    LOOKSTAT,

    /**
     * Просмтор статистики созданий.
     */
    LOOKMAKESTAT,

    /**
     * Изменение языка.
     */
    CHANGELOCALE,

    /**
     * Работа с пользователями.
     */
    USERDEAL,

    /**
     * Повышение пользователя до tutor.
     */
    UPGRADEUSER,

    /**
     * Понижение пользователя до student.
     */
    DOWNGRADEUSER,

    /**
     * Удаление пользователя к чертям собачьим.
     */
    DELETEUSER

}
