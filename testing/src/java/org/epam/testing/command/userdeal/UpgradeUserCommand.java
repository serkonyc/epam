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

/**
 * Команда повышения одного пользователя.
 *
 * @author Sergiusz
 */
public class UpgradeUserCommand extends AbstractCommand {

    /**
     * Команда повышения одного пользователя.
     *
     * @param request Запрос, переданный с jsp-страницы.
     * @return String-адрес определённой в свойствах страницы.
     * @throws LogicException в случае проблем с DaoFactory.
     * @throws TechException в случае технических проблем.
     */
    @Override
    public String perform(HttpServletRequest request) throws LogicException, TechException {
        AbstractDao dao = new DaoFactory().getDaoByName("user");
        dao.updateByParameter(
                request.getParameter("id"),
                request.getParameter("nick"),
                request.getParameter("email"),
                "tutor"
        );
        ArrayList users = dao.selectAll();

        request.setAttribute("smartredir", "userdeal");
        request.setAttribute("users", users);
        return flowPagePropertyHandler.getPropertyValue(this.getClass().getSimpleName());
    }

}
