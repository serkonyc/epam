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
 * Команда выбора предмета из предложенного списка для создания теста.
 *
 * @author Sergiusz
 */
public class ChooseSubjectCommand extends AbstractCommand {

    /**
     * Команда выбора предмета из предложенного списка для создания теста.
     *
     * @param request Запрос, переданный с jsp-страницы.
     * @return String-адрес страницы для выбора темы.
     * @throws LogicException в случае проблем с i18nDealer.
     * @throws TechException в случае технических проблем.
     */
    @Override
    public String perform(HttpServletRequest request) throws LogicException, TechException {
        new I18nDealer(this.getClass().getSimpleName()).assignLocale(request);
        if (request.getParameter("letter") == null) {
            int subjectId = 0;
            AbstractDao dao = new DaoFactory().getDaoByName("theme");
            ArrayList<Theme> themes = dao.selectAllByParameter(request.getParameter("input"));
            dao = new DaoFactory().getDaoByName("subject");
            if (!dao.isExist(request.getParameter("input"))) {
                dao.insertNew(request.getParameter("input"));
            }
            ArrayList<Subject> subjects = dao.selectAll();
            for (Subject subject : subjects) {
                if (subject.getName().equals(request.getParameter("input"))) {
                    subjectId = subject.getId();
                    break;
                }
            }

            request.setAttribute("themes", themes);
            request.setAttribute("oldinput", request.getParameter("input"));
            request.setAttribute("wascommand", "themes");
            request.setAttribute("subjid", subjectId);
        } else {
            AbstractDao dao = new DaoFactory().getDaoByName("subject");
            ArrayList<Subject> subjects;
            ArrayList<Subject> earlySubjects = dao.selectAll();
            String bigLetter = request.getParameter("letter").toUpperCase();
            String litLetter = request.getParameter("letter").toLowerCase();
            subjects = new ArrayList();
            for (Subject subj : earlySubjects) {
                if (subj.getName().startsWith(bigLetter)
                        || subj.getName().startsWith(litLetter)) {
                    subjects.add(subj);
                }
            }

            request.setAttribute("subjs", subjects);
            request.setAttribute("subnum", subjects.size());
            request.setAttribute("wascommand", "prepare");
        }

        request.setAttribute("latAB", lat);
        request.setAttribute("kirAB", kir);
        return flowPagePropertyHandler.getPropertyValue(this.getClass().getSimpleName());
    }

}
