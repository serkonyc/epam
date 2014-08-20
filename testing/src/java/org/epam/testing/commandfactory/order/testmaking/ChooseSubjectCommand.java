/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.testing.commandfactory.order.testmaking;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.epam.testing.commandfactory.order.AbstractCommand;
import org.epam.testing.daofactory.DaoFactory;
import org.epam.testing.daofactory.dao.AbstractDao;
import org.epam.testing.daofactory.entity.Subject;
import org.epam.testing.daofactory.entity.Theme;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;

/**
 *
 * @author Sergiusz
 */
public class ChooseSubjectCommand extends AbstractCommand {

    @Override
    public String perform(HttpServletRequest request) throws LogicException, TechException {
        int subjectId = 0;
        if (request.getParameter("newsubject") == null) {
            AbstractDao dao = new DaoFactory().getDaoByName("theme");
            ArrayList<Theme> themes = dao.selectAllByParameter(request.getParameter("input"));
            dao = new DaoFactory().getDaoByName("subject");
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
            return "/jsp/addtest.jsp";
        } else {
            AbstractDao dao = new DaoFactory().getDaoByName("subject");
            if (!dao.isExist(request.getParameter("input"))) {
                dao.insertNew(request.getParameter("input"));
            }
            ArrayList<Subject> subjects = dao.selectAll();

            request.setAttribute("wascommand", "prepare");
            request.setAttribute("subjs", subjects);
            return "/jsp/addtest.jsp";
        }
    }

}
