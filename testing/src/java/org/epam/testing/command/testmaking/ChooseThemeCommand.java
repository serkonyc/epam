/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.command.testmaking;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.epam.testing.command.common.AbstractCommand;
import org.epam.testing.dao.factory.DaoFactory;
import org.epam.testing.dao.AbstractDao;
import org.epam.testing.dao.entity.Subject;
import org.epam.testing.dao.entity.Theme;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;
import org.epam.testing.utils.I18nDealer;

/**
 * Команда выбора темы из предложенного списка для создания теста.
 *
 * @author Sergiusz
 */
public class ChooseThemeCommand extends AbstractCommand {

    /**
     * Команда выбора темы из предложенного списка для создания теста.
     *
     * @param request Запрос, переданный с jsp-страницы.
     * @return String-адрес страницы с формой создания теста.
     * @throws LogicException в случае проблем с i18nDealer.
     * @throws TechException в случае технических проблем.
     */
    @Override
    public String perform(HttpServletRequest request) throws LogicException, TechException {
        new I18nDealer(this.getClass().getSimpleName()).assignLocale(request);
        int themeId = 0;
        AbstractDao dao = new DaoFactory().getDaoByName("theme");
        if (request.getParameter("letter") == null) {
            if (!dao.isExist(request.getParameter("input"))) {
                dao.insertNew(request.getParameter("inputid"), request.getParameter("input"));
            }
            int questNum = Integer.parseInt(request.getParameter("questnum"));
            ArrayList<Theme> themes = dao.selectAll();
            for (Theme theme : themes) {
                if (theme.getName().equals(request.getParameter("input"))) {
                    themeId = theme.getId();
                    break;
                }
            }
            request.setAttribute("questarr", new ArrayList(questNum));
            request.setAttribute("questnum", questNum);
            request.setAttribute("wascommand", "questions");
            request.setAttribute("themeid", themeId);
        } else {
            int subjectId = 0;
            ArrayList<Theme> themes;
            ArrayList<Theme> earlyThemes = dao.selectAllByParameter(request.getParameter("oldinput"));
            String bigLetter = request.getParameter("letter").toUpperCase();
            String litLetter = request.getParameter("letter").toLowerCase();
            themes = new ArrayList();
            for (Theme theme : earlyThemes) {
                if (theme.getName().startsWith(bigLetter)
                        || theme.getName().startsWith(litLetter)) {
                    themes.add(theme);
                }
            }
            request.setAttribute("themes", themes);
            request.setAttribute("wascommand", "themes");
            dao = new DaoFactory().getDaoByName("subject");
            ArrayList<Subject> subjects = dao.selectAll();
            for (Subject subject : subjects) {
                if (subject.getName().equals(request.getParameter("oldinput"))) {
                    subjectId = subject.getId();
                    break;
                }
            }
            request.setAttribute("oldinput", request.getParameter("oldinput"));
            request.setAttribute("subjid", subjectId);
            request.setAttribute("latAB", lat);
            request.setAttribute("kirAB", kir);
        }
        return flowPagePropertyHandler.getPropertyValue(this.getClass().getSimpleName());
    }
}
