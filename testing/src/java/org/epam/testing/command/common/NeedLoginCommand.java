/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.command.common;

import javax.servlet.http.HttpServletRequest;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;
import org.epam.testing.utils.I18nDealer;

/**
 * Команда требования аутентификации пользователем.
 * 
 * @author Sergiusz
 */
public class NeedLoginCommand extends AbstractCommand {

    /**
     * Команда требования аутентификации пользователем.
     * 
     * @param request Запрос, переданный с jsp-страницы.
     * @return String-адрес страницы аутентификации.
     * @throws LogicException в случае проблем с логикой I18nDealer.
     * @throws TechException в случае технических ошибок.
     */
    @Override
    public String perform(HttpServletRequest request) throws LogicException, TechException {
        request.setAttribute("logorreg", "login");
        new I18nDealer(this.getClass().getSimpleName()).assignLocale(request);
        return flowPagePropertyHandler.getPropertyValue(this.getClass().getSimpleName());
    }

}
