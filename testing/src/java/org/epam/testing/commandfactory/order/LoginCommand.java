/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.testing.commandfactory.order;

import java.util.ArrayList;
import java.util.TreeSet;
import javax.servlet.http.HttpServletRequest;
import org.epam.testing.daofactory.DaoFactory;
import org.epam.testing.daofactory.dao.AbstractDao;
import org.epam.testing.daofactory.entity.Subject;
import org.epam.testing.daofactory.entity.Test;
import org.epam.testing.daofactory.entity.Theme;
import org.epam.testing.daofactory.entity.User;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;

/**
 *
 * @author Sergiusz
 */
public class LoginCommand extends AbstractCommand {

    @Override
    public String perform(HttpServletRequest request) throws LogicException, TechException {
        String login = null;
        boolean ifLoginExists = false;
        AbstractDao dao = null;

        if (request.getSession().getAttribute("id") == null) {
            if (request.getParameter("login") == null && request.getParameter("pass") == null) {
                return "/jspview/welcome.jsp";
            }
            dao = new DaoFactory().getDaoByName("user");
            ArrayList<User> users = dao.selectAll();
            login = request.getParameter("login");
            String pass = request.getParameter("pass");
            for (User user : users) {
                if (user.getNick().equals(login)) {
                    ifLoginExists = true;
                    if (user.getPass().equals(pass)) {
                        int id = user.getId();
                        dao = new DaoFactory().getDaoByName("test");
                        ArrayList<Test> tests = dao.selectAll();
                        TreeSet<Subject> subjs = new TreeSet<>();
                        TreeSet<Theme> themes = new TreeSet<>();
                        for (Test test : tests) {
                            subjs.add(test.getTheme().getSubj());
                            themes.add(test.getTheme());
                        }

                        request.getSession().setAttribute("id", id);
                        request.getSession().setAttribute("nick", login);
                        request.setAttribute("subjs", subjs);
                        request.setAttribute("themes", themes);
                        request.setAttribute("tests", tests);
                        return "/jspview/postlog.jsp";
                    }
                }
            }
        } else {
            dao = new DaoFactory().getDaoByName("test");
            ArrayList<Test> tests = dao.selectAll();
            TreeSet<Subject> subjs = new TreeSet<>();
            TreeSet<Theme> themes = new TreeSet<>();
            for (Test test : tests) {
                subjs.add(test.getTheme().getSubj());
                themes.add(test.getTheme());
            }

            request.setAttribute("subjs", subjs);
            request.setAttribute("themes", themes);
            request.setAttribute("tests", tests);
            return "/jspview/postlog.jsp";
        }

        if (ifLoginExists) {
            request.setAttribute("nick", login);
            request.setAttribute("logorreg", "logexfailure");
        } else {
            request.setAttribute("logorreg", "logfailure");
        }

        return "/jspview/welcome.jsp";
    }

}
