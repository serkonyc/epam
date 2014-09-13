/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.command.testmanaging;

import java.util.ArrayList;
import java.util.TreeSet;
import javax.servlet.http.HttpServletRequest;
import org.epam.testing.command.common.AbstractCommand;
import org.epam.testing.dao.factory.DaoFactory;
import org.epam.testing.dao.AbstractDao;
import org.epam.testing.dao.entity.Subject;
import org.epam.testing.dao.entity.Test;
import org.epam.testing.dao.entity.Theme;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;
import org.epam.testing.utils.I18nDealer;

/**
 * Команда вывода списков тестов для изменения.
 *
 * @author Sergiusz
 */
public class ChangeTestCommand extends AbstractCommand {

    /**
     * Команда вывода списков тестов для изменения.
     *
     * @param request Запрос, переданный с jsp-страницы.
     * @return String-адрес главной страницы с изменёнными параметрами.
     * @throws LogicException в случае проблем с i18nDealer или DaoFactory.
     * @throws TechException в случае технических проблем.
     */
    @Override
    public String perform(HttpServletRequest request) throws LogicException, TechException {
        new I18nDealer(this.getClass().getSimpleName()).assignLocale(request);
        AbstractDao dao = new DaoFactory().getDaoByName("test");
        ArrayList<Test> tests = dao.selectAll();
        TreeSet<Subject> subjs = new TreeSet<>();
        TreeSet<Theme> themes = new TreeSet<>();
        for (Test test : tests) {
            subjs.add(test.getTheme().getSubj());
            themes.add(test.getTheme());
        }

        request.setAttribute("wascommand", "change");
        request.setAttribute("subjs", subjs);
        request.setAttribute("themes", themes);
        request.setAttribute("tests", tests);
        return flowPagePropertyHandler.getPropertyValue(this.getClass().getSimpleName());
    }

}
