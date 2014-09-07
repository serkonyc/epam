/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.testing.command.common;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;
import org.epam.testing.dao.factory.DaoFactory;
import org.epam.testing.dao.AbstractDao;
import org.epam.testing.dao.entity.User;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;
import org.epam.testing.utils.I18nDealer;

/**
 *
 * @author Sergiusz
 */
public class RegisterCommand extends AbstractCommand {

    @Override
    public String perform(HttpServletRequest request) throws LogicException, TechException {
        new I18nDealer(this.getClass().getSimpleName()).assignLocale(request);
        AbstractDao dao = new DaoFactory().getDaoByName("user");
        ArrayList<User> users = dao.selectAll();
        String[] args = new String[4];
        args[0] = request.getParameter("login");
        args[1] = request.getParameter("email");
        args[2] = request.getParameter("pass1");
        args[3] = request.getParameter("pass2");
        if ((args[1].equals("")
                || args[1].matches("[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[A-Za-z]+"))
                && args[0].matches("[A-Za-z0-9_]{4,16}")
                && args[2].equals(args[3])) {
            args[3] = DigestUtils.md5Hex(args[3]);
            for (User user : users) {
                if (user.getNick().equals(args[0])) {
                    request.setAttribute("logorreg", "regexfailure");
                    return flowPagePropertyHandler.getPropertyValue(this.getClass().getSimpleName().concat("err"));
                }
            }
            args[2] = "student";
            dao.insertNew(args);
            int id = dao.getLastId();

            request.getSession().setAttribute("id", id);
            request.getSession().setAttribute("nick", args[0]);
            return null;
        }
        return null;
    }
}
