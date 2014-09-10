/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.testing.command.statistics;

import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import org.epam.testing.command.common.AbstractCommand;
import org.epam.testing.dao.AbstractDao;
import org.epam.testing.dao.entity.PassHistory;
import org.epam.testing.dao.entity.Test;
import org.epam.testing.dao.factory.DaoFactory;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;
import org.epam.testing.utils.ByPageReflector;
import org.epam.testing.utils.I18nDealer;

/**
 *
 * @author Sergiusz
 */
public class LookPassStatCommand extends AbstractCommand {

    @Override
    public String perform(HttpServletRequest request) throws LogicException, TechException {
        new I18nDealer(this.getClass().getSimpleName()).assignLocale(request);
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
            Collections.reverse(history);
            Collections.reverse(posttestlist);
            for (int i = 0; i < 9; i++) {
                history.add(history.size(), new PassHistory(0));
                posttestlist.add(posttestlist.size(), new Test(0));
            }

            int pagenum;
            String currpagenum = request.getParameter("pagenum");
            ArrayList allpagesnum = new ArrayList();
            if (currpagenum == null || currpagenum.equals("...")) {
                currpagenum = "1";
            }
            pagenum = Integer.parseInt(currpagenum);
            ByPageReflector.makeOutput(allpagesnum, pagenum, posttestlist.size() / 10);
            
            request.setAttribute("currpage", pagenum);
            request.setAttribute("posttestlist", new ArrayList(posttestlist.subList(10 * pagenum - 10, 10 * (pagenum))));
            request.setAttribute("allpagesnum", allpagesnum);
            request.setAttribute("history", new ArrayList(history.subList(10 * pagenum - 10, 10 * (pagenum))));
            return flowPagePropertyHandler.getPropertyValue(this.getClass().getSimpleName());
        } else {
            return null;
        }
    }

}
