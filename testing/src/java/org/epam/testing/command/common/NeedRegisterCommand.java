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
 * Команда требования регистрации пользователем.
 * 
 * @author Sergiusz
 */
public class NeedRegisterCommand extends AbstractCommand {

    /**
     * Команда требования регистрации пользователем.
     * 
     * @param request Запрос, переданный с jsp-страницы.
     * @return String-адрес страницы регистрации.
     * @throws LogicException в случае проблем с логикой I18nDealer.
     * @throws TechException в случае технических ошибок.
     */
    @Override
    public String perform(HttpServletRequest request) throws LogicException, TechException {
        request.setAttribute("logorreg", "register");
        new I18nDealer(this.getClass().getSimpleName()).assignLocale(request);
        return flowPagePropertyHandler.getPropertyValue(this.getClass().getSimpleName());
    }

}
