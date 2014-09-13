/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.command.common;

import javax.servlet.http.HttpServletRequest;
import org.epam.testing.control.CommandController;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;
import org.epam.testing.prophandler.PropertyHandler;

/**
 * Абстрактный класс команды.
 *
 * @author Sergiusz
 */
public abstract class AbstractCommand {

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(CommandController.class.getSimpleName());

    /**
     * Доступ к свойствам, назначающим каждой команде адрес jsp-страницы.
     */
    protected static PropertyHandler flowPagePropertyHandler;

    /**
     * Доступ к свойсвам, назначающим каждой локали необходимые языковые версии
     * надписей.
     */
    protected static PropertyHandler localePropertyHandler;

    /**
     * Массив символов латинского алфавита.
     */
    protected static char[] lat = "ABCDEFGHIJGKLMNOPQRSTUVWXYZ".toCharArray();

    /**
     * Массив символов кириллического алфавита.
     */
    protected static char[] kir = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЭЮЯ".toCharArray();

    static {
        try {
            flowPagePropertyHandler = new PropertyHandler("pageflow.properties");
        } catch (TechException ex) {
            LOGGER.error(ex);
            throw new ExceptionInInitializerError();
        }
    }

    /**
     * Единый метод выполнения для каждой команды.
     *
     * @param request Запрос, переданный с jsp-страницы.
     * @return String с относительным адресом jsp-страницы для перехода.
     * @throws LogicException в случае проблем с логикой дейстий.
     * @throws TechException в случае технических проблем.
     */
    abstract public String perform(HttpServletRequest request) throws LogicException, TechException;

}
