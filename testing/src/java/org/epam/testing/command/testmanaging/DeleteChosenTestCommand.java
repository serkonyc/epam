/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.command.testmanaging;

import javax.servlet.http.HttpServletRequest;
import org.epam.testing.command.common.AbstractCommand;
import org.epam.testing.dao.factory.DaoFactory;
import org.epam.testing.dao.AbstractDao;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;

/**
 * Команда окончательного удаления выбранного теста.
 *
 * @author Sergiusz
 */
public class DeleteChosenTestCommand extends AbstractCommand {

    /**
     * Команда окончательного удаления выбранного теста.
     *
     * @param request Запрос, переданный с jsp-страницы.
     * @return null для перехода на главную.
     * @throws LogicException в случае проблем DaoFactory.
     * @throws TechException в случае технических проблем.
     */
    @Override
    public String perform(HttpServletRequest request) throws LogicException, TechException {
        AbstractDao dao = new DaoFactory().getDaoByName("test");
        dao.deleteByParameter(request.getParameter("data"));

        return null;
    }

}
