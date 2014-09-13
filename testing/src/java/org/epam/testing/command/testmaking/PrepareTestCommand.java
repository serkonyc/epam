/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.command.testmaking;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.epam.testing.command.common.AbstractCommand;
import org.epam.testing.dao.AbstractDao;
import org.epam.testing.dao.entity.Subject;
import org.epam.testing.dao.entity.Test;
import org.epam.testing.dao.entity.Theme;
import org.epam.testing.dao.factory.DaoFactory;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;
import org.epam.testing.utils.I18nDealer;

/**
 * Команда подготовки создания теста и загрузки списка предметов.
 *
 * @author Sergiusz
 */
public class PrepareTestCommand extends AbstractCommand {

    /**
     * Команда подготовки создания теста и загрузки списка предметов.
     *
     * @param request Запрос, переданный с jsp-страницы.
     * @return адрес страницы для выбора предмета.
     * @throws LogicException в случае проблем с i18nDealer или DaoFactory.
     * @throws TechException в случае технических проблем.
     */
    @Override
    public String perform(HttpServletRequest request) throws LogicException, TechException {
        new I18nDealer(this.getClass().getSimpleName()).assignLocale(request);
        AbstractDao dao = new DaoFactory().getDaoByName("test");
        ArrayList<Test> tests = dao.selectAll();
        dao = new DaoFactory().getDaoByName("subject");
        ArrayList<Subject> subjects = dao.selectAll();
        ArrayList<Subject> finalSubjects = new ArrayList<>();

        for (Subject subject : subjects) {
            boolean ishastest = false;
            for (Test test : tests) {
                if (subject.getId() == test.getTheme().getSubj().getId()) {
                    ishastest = true;
                    break;
                }
            }
            if (!ishastest) {
                dao.deleteByParameter(String.valueOf(subject.getId()));
            } else {
                finalSubjects.add(subject);
            }
        }
        dao = new DaoFactory().getDaoByName("theme");
        ArrayList<Theme> themes = dao.selectAll();
        for (Theme theme : themes) {
            boolean ishastest = false;
            for (Test test : tests) {
                if (theme.getId() == test.getTheme().getId()) {
                    ishastest = true;
                    break;
                }
            }
            if (!ishastest) {
                dao.deleteByParameter(String.valueOf(theme.getId()));
            }
        }

        request.setAttribute("wascommand", "prepare");
        request.setAttribute("subjs", finalSubjects);
        request.setAttribute("subnum", finalSubjects.size());
        request.setAttribute("latAB", lat);
        request.setAttribute("kirAB", kir);
        return flowPagePropertyHandler.getPropertyValue(this.getClass().getSimpleName());
    }

}
