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
        AbstractDao dao = new DaoFactory().getDaoByName("subject");
        if (!dao.isExist(request.getParameter("input"))) {
            dao.insertNew(request.getParameter("input"));
        } else {
            AbstractDao elsedao = new DaoFactory().getDaoByName("theme");
            ArrayList<Theme> themes = elsedao.selectAllByParameter(request.getParameter("input"));
            request.setAttribute("themes", themes);
        }
        ArrayList<Subject> subjects = dao.selectAll();
        for (Subject subject : subjects) {
            if (subject.getName().equals(request.getParameter("input"))) {
                subjectId = subject.getId();
                break;
            }
        }
        
        request.setAttribute("wascommand", "themes");
        request.setAttribute("subjid", subjectId);
        return "/jspview/addtest.jsp";
    }

}
