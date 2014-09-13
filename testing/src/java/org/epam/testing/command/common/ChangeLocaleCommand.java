/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.command.common;

import javax.servlet.http.HttpServletRequest;

/**
 * Команда смены локали.
 *
 * @author Sergiusz
 */
public class ChangeLocaleCommand extends AbstractCommand {

    /**
     * Команда смены локали.
     *
     * @param request Запрос, переданный с jsp-страницы.
     * @return null, по которому осуществится перенаправление с помощью фильтра.
     */
    @Override
    public String perform(HttpServletRequest request) {
        request.getSession().setAttribute("lang", request.getParameter("lang"));
        return null;
    }

}
