/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.testing.command.statistics;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.epam.testing.command.common.AbstractCommand;
import org.epam.testing.dao.factory.DaoFactory;
import org.epam.testing.dao.AbstractDao;
import org.epam.testing.dao.entity.PassHistory;
import org.epam.testing.dao.entity.Test;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;

/**
 *
 * @author Sergiusz
 */
public class LookPassStatCommand extends AbstractCommand {

    @Override
    public String perform(HttpServletRequest request) throws LogicException, TechException {
        if (request.getParameter("progress") == null) {
            int id = Integer.parseInt(request.getSession().getAttribute("id").toString());

            AbstractDao dao = new DaoFactory().getDaoByName("passhistory");
            ArrayList<PassHistory> history = dao.selectAllByParameter(String.valueOf(id));
            dao = new DaoFactory().getDaoByName("test");
            ArrayList<Test> pretestlist = dao.selectAll();
            ArrayList<Test> posttestlist = new ArrayList<>();
            for (PassHistory pass : history) {
                for (Test test : pretestlist) {
                    if (pass.getTestId() == test.getId()) {
                        posttestlist.add(test);
                        break;
                    }
                }
            }
            request.setAttribute("posttestlist", posttestlist);
            request.setAttribute("history", history);
            return flowPagePropertyHandler.getPropertyValue(this.getClass().getSimpleName());
        } else {
            return null;
        }
    }

}
