/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.command.userdeal;

import java.util.ArrayList;
import java.util.TreeSet;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;
import org.epam.testing.command.common.AbstractCommand;
import org.epam.testing.dao.AbstractDao;
import org.epam.testing.dao.entity.Subject;
import org.epam.testing.dao.entity.Test;
import org.epam.testing.dao.entity.Theme;
import org.epam.testing.dao.entity.User;
import org.epam.testing.dao.factory.DaoFactory;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;
import org.epam.testing.utils.I18nDealer;

/**
 *
 * @author Sergiusz
 */
public class ChangePassCommand extends AbstractCommand {

    @Override
    public String perform(HttpServletRequest request) throws LogicException, TechException {
        new I18nDealer(this.getClass().getSimpleName()).assignLocale(request);
        String oldpass = DigestUtils.md5Hex(request.getParameter("oldpass"));
        String newpass = DigestUtils.md5Hex(request.getParameter("newpass"));
        String newpasscheck = DigestUtils.md5Hex(request.getParameter("newpasscheck"));
        if (newpass != null && newpass.equals(newpasscheck)) {
            AbstractDao dao = new DaoFactory().getDaoByName("user");
            ArrayList<User> users = dao.selectAll();
            for (User user : users) {
                if (user.getId() == (Integer.parseInt(request.getSession().getAttribute("id").toString()))) {
                    if (user.getPass().equals(oldpass)) {
                        dao.updateByParameter(
                                request.getSession().getAttribute("id").toString(),
                                newpass
                        );
                        request.setAttribute("changed", "yes");
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
                        request.getSession().setAttribute("errorMess", null);
                        return flowPagePropertyHandler.getPropertyValue(this.getClass().getSimpleName());
                    }
                }
            }
        }
        request.setAttribute("changed", "no");
        AbstractDao dao = new DaoFactory().getDaoByName("test");
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
        request.getSession().setAttribute("errorMess", null);
        return flowPagePropertyHandler.getPropertyValue(this.getClass().getSimpleName());
    }

}
