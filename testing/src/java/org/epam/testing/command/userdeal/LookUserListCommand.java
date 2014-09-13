/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.command.userdeal;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.epam.testing.command.common.AbstractCommand;
import org.epam.testing.dao.AbstractDao;
import org.epam.testing.dao.factory.DaoFactory;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;
import org.epam.testing.utils.I18nDealer;

/**
 * Команда просмотра таблицы пользователей.
 *
 * @author Sergiusz
 */
public class LookUserListCommand extends AbstractCommand {

    /**
     * Команда просмотра таблицы пользователей.
     *
     * @param request Запрос, переданный с jsp-страницы.
     * @return String-адрес определённой в свойствах страницы.
     * @throws LogicException в случае проблем с i18nDealer или DaoFactory.
     * @throws TechException в случае технических проблем.
     */
    @Override
    public String perform(HttpServletRequest request) throws LogicException, TechException {
        new I18nDealer(this.getClass().getSimpleName()).assignLocale(request);
        AbstractDao dao = new DaoFactory().getDaoByName("user");
        ArrayList users = dao.selectAll();

        request.setAttribute("smartredir", "userdeal");
        request.setAttribute("users", users);
        return flowPagePropertyHandler.getPropertyValue(this.getClass().getSimpleName());
    }

}
