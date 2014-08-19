/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.testing.commandfactory.order.testmanaging;

import java.util.ArrayList;
import java.util.TreeSet;
import javax.servlet.http.HttpServletRequest;
import org.epam.testing.commandfactory.order.AbstractCommand;
import org.epam.testing.daofactory.DaoFactory;
import org.epam.testing.daofactory.dao.AbstractDao;
import org.epam.testing.daofactory.entity.Subject;
import org.epam.testing.daofactory.entity.Test;
import org.epam.testing.daofactory.entity.Theme;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;

/**
 *
 * @author Sergiusz
 */
public class ChangeTestCommand extends AbstractCommand {

    @Override
    public String perform(HttpServletRequest request) throws LogicException, TechException {
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
        return "/jsp/postlog.jsp";
    }

}
