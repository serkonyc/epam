/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @author Sergiusz
 */
public class ChooseThemeCommand extends AbstractCommand {

    @Override
    public String perform(HttpServletRequest request) throws LogicException, TechException {
        new I18nDealer(this.getClass().getSimpleName()).assignLocale(request);
        int themeId = 0;
        AbstractDao dao = new DaoFactory().getDaoByName("theme");
        if (!dao.isExist(request.getParameter("input"))) {
            dao.insertNew(request.getParameter("inputid"), request.getParameter("input"));
        }
        if (request.getParameter("newtheme") == null) {
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
            dao = new DaoFactory().getDaoByName("theme");
            ArrayList<Theme> themes = dao.selectAllByParameter(request.getParameter("oldinput"));
            dao = new DaoFactory().getDaoByName("subject");
            ArrayList<Subject> subjects = dao.selectAll();
            for (Subject subject : subjects) {
                if (subject.getName().equals(request.getParameter("oldinput"))) {
                    subjectId = subject.getId();
                    break;
                }
            }

            request.setAttribute("oldinput", request.getParameter("oldinput"));
            request.setAttribute("themes", themes);
            request.setAttribute("wascommand", "themes");
            request.setAttribute("subjid", subjectId);
        }
        return flowPagePropertyHandler.getPropertyValue(this.getClass().getSimpleName());
    }
}