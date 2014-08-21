/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.testing.commandfactory.order.statistics;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.epam.testing.commandfactory.order.AbstractCommand;
import org.epam.testing.daofactory.DaoFactory;
import org.epam.testing.daofactory.dao.AbstractDao;
import org.epam.testing.daofactory.entity.Test;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;

/**
 *
 * @author Sergiusz
 */
public class LookMakeStatCommand extends AbstractCommand {

    @Override
    public String perform(HttpServletRequest request) throws LogicException, TechException {
        if (request.getParameter("progress") == null) {
            int id = Integer.parseInt(request.getSession().getAttribute("id").toString());

            AbstractDao dao = new DaoFactory().getDaoByName("test");
            ArrayList<Test> pretestlist = dao.selectAll();
            ArrayList<Test> posttestlist = new ArrayList<>();
            for (Test test : pretestlist) {
                if (id == test.getTutorId()) {
                    posttestlist.add(test);
                }
            }
            request.setAttribute("posttestlist", posttestlist);
            return flowPagePropertyHandler.getPropertyValue(this.getClass().getSimpleName());
        } else {
            return null;
        }
    }
}
