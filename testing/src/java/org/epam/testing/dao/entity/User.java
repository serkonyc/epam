/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.dao.entity;

/**
 * Сущность пользователя.
 *
 * @author Sergiusz
 */
public class User extends AbstractEntity {

    private String nick;
    private String email;
    private String role;
    private String pass;

    /**
     * Конструктор создания объекта пользователя.
     *
     * @param id идентификатор сущности БД
     * @param args Параметры создания пользователя: nick, email, role, password.
     */
    public User(int id, String... args) {
        super(id);
        this.nick = args[0];
        this.email = args[1];
        this.role = args[2];
        this.pass = args[3];
    }

    /**
     * Получение псевдонима пользователя.
     *
     * @return Псевдоним пользователя
     */
    public String getNick() {
        return nick;
    }

    /**
     * Получение электронного почтового адреса пользователя.
     *
     * @return Эмеил пользователя.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Получение роли пользователя.
     *
     * @return Роль пользователя (admin, tutor, student)
     */
    public String getRole() {
        return role;
    }

    /**
     * Получение пароль пользователя.
     *
     * @return Пароль в виде md5-записи
     */
    public String getPass() {
        return pass;
    }

}
