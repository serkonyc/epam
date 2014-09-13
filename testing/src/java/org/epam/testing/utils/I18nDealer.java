/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.utils;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;
import org.epam.testing.prophandler.PropertyHandler;

/**
 * Класс, отвечающий за интернационализацию, выбор языка страниц.
 *
 * @author Sergiusz
 */
public class I18nDealer {

    private String commandName;
    private PropertyHandler localePropertyHandler;

    /**
     * Конструктор класса.
     *
     * @param commandName Имя команды, вызвавшей класс.
     */
    public I18nDealer(String commandName) {
        this.commandName = commandName;
    }

    /**
     * Метод присваивания языковых данных объекту request.
     * @param request Объект запроса, заполняемый данными.
     * @throws TechException
     * @throws LogicException
     */
    public void assignLocale(HttpServletRequest request) throws TechException, LogicException {
        if (request.getParameter("lang") != null) {
            request.getSession().setAttribute("lang", request.getParameter("lang"));
        } else if (request.getSession(true).getAttribute("lang") == null) {
            request.getSession().setAttribute("lang", "label_en");
        }
        localePropertyHandler = new PropertyHandler(
                request.getSession()
                .getAttribute("lang")
                .toString()
                .concat(".properties")
        );
        HashMap local = new HashMap();
        Object[] tempLocal = localePropertyHandler.getKeysSet().toArray();
        switch (commandName) {
            case "BeginCommand":
                for (Object key : tempLocal) {
                    if (key.toString().startsWith("A")
                            || key.toString().startsWith("B")) {
                        local.put(key.toString(), localePropertyHandler.getPropertyValue(key.toString()));
                    }
                }
                break;
            case "LoginCommand":
                local = new HashMap();
                for (Object key : tempLocal) {
                    if (key.toString().startsWith("A")
                            || key.toString().startsWith("B")
                            || key.toString().startsWith("C")) {
                        local.put(key.toString(), localePropertyHandler.getPropertyValue(key.toString()));
                    }
                }
                request.setAttribute("local", local);
                break;
            case "RegisterCommand":
                for (Object key : tempLocal) {
                    if (key.toString().startsWith("A")
                            || key.toString().startsWith("B")) {
                        local.put(key.toString(), localePropertyHandler.getPropertyValue(key.toString()));
                    }
                }
                break;
            case "NeedRegisterCommand":
                for (Object key : tempLocal) {
                    if (key.toString().startsWith("B")) {
                        local.put(key.toString(), localePropertyHandler.getPropertyValue(key.toString()));
                    }
                }
                break;
            case "NeedLoginCommand":
                for (Object key : tempLocal) {
                    if (key.toString().startsWith("A")) {
                        local.put(key.toString(), localePropertyHandler.getPropertyValue(key.toString()));
                    }
                }
                break;
            default:
                local = new HashMap();
                for (Object key : tempLocal) {
                    local.put(key.toString(), localePropertyHandler.getPropertyValue(key.toString()));
                }
        }
        request.setAttribute("local", local);
    }

}
