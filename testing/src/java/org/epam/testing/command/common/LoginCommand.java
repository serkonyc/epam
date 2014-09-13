/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.command.common;

import java.util.ArrayList;
import java.util.TreeSet;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;
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
 * Команда аутентификации, обрабатывающая ошибки и попытки войти в систему либо
 * возвращающая пользователя на главную рабочую страницу.
 *
 * @author Sergiusz
 */
public class LoginCommand extends AbstractCommand {

    /**
     * Команда аутентификации, обрабатывающая ошибки и попытки войти в систему
     * либо возвращающая пользователя на главную рабочую страницу.
     *
     * @param request Запрос, переданный с jsp-страницы.
     * @return String c адресом jsp либо null в случае сброса на главную
     * страницу.
     * @throws LogicException в случае проблем с логикой I18nDealer.
     * @throws TechException в случае технических ошибок.
     */
    @Override
    public String perform(HttpServletRequest request) throws LogicException, TechException {
        new I18nDealer(this.getClass().getSimpleName()).assignLocale(request);
        if (request.getParameter("progress") == null) {
            String login;
            boolean ifLoginExists = false;

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

            if (request.getSession().getAttribute("id") == null) {
                if (request.getParameter("login") == null && request.getParameter("pass") == null) {
                    return flowPagePropertyHandler.getPropertyValue(this.getClass().getSimpleName().concat("err"));
                }
                dao = new DaoFactory().getDaoByName("user");
                ArrayList<User> users = dao.selectAll();
                login = request.getParameter("login");
                String pass = DigestUtils.md5Hex(request.getParameter("pass"));
                for (User user : users) {
                    if (user.getNick().equals(login)) {
                        ifLoginExists = true;
                        if (user.getPass().equals(pass)) {
                            int id = user.getId();
                            String role = user.getRole();

                            request.getSession().setAttribute("id", id);
                            request.getSession().setAttribute("nick", login);
                            request.getSession().setAttribute("role", role);
                            return null;
                        }
                    }
                }
            } else {
                return flowPagePropertyHandler.getPropertyValue(this.getClass().getSimpleName());
            }

            if (ifLoginExists) {
                request.setAttribute("nick", login);
                request.setAttribute("logorreg", "logexfailure");
            } else {
                request.setAttribute("logorreg", "logfailure");
            }
            return flowPagePropertyHandler.getPropertyValue(this.getClass().getSimpleName().concat("err"));
        } else {
            return null;
        }
    }
}
