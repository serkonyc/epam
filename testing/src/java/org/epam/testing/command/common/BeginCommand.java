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
 * Команда возвращения к неидентифицированному пользователю.
 *
 * @author Sergiusz
 */
public class BeginCommand extends AbstractCommand {

    /**
     * Команда возвращения к неидентифицированному пользователю.
     *
     * @param request Запрос, переданный с jsp-страницы.
     * @return null, по которому осуществится перенаправление с помощью фильтра.
     * @throws LogicException в случае проблем с логикой I18nDealer.
     * @throws TechException в случае технических ошибок.
     */
    @Override
    public String perform(HttpServletRequest request) throws LogicException, TechException {
        new I18nDealer(this.getClass().getSimpleName()).assignLocale(request);
        request.getSession().setAttribute("id", null);
        request.getSession().setAttribute("nick", null);
        request.getSession().setAttribute("result", null);
        request.getSession().setAttribute("jsppath", null);
        request.getSession().setAttribute("role", null);
        return null;
    }

}
