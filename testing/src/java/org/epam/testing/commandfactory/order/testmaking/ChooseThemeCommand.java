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
import org.epam.testing.daofactory.entity.Theme;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;

/**
 *
 * @author Sergiusz
 */
public class ChooseThemeCommand extends AbstractCommand {

    @Override
    public String perform(HttpServletRequest request) throws LogicException, TechException {
        int questNum = Integer.parseInt(request.getParameter("questnum"));
        int themeId = 0;
        AbstractDao dao = new DaoFactory().getDaoByName("theme");
        if (!dao.isExist(request.getParameter("input"))) {
            dao.insertNew(request.getParameter("inputid"), request.getParameter("input"));
        }
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
        return "/jspview/addtest.jsp";
    }
}
