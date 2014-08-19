/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.testing.commandfactory.order.testmanaging;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.epam.testing.commandfactory.order.AbstractCommand;
import org.epam.testing.daofactory.DaoFactory;
import org.epam.testing.daofactory.dao.AbstractDao;
import org.epam.testing.daofactory.entity.Answer;
import org.epam.testing.daofactory.entity.Quest;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;

/**
 *
 * @author Sergiusz
 */
public class ChangeChosenTestCommand extends AbstractCommand {

    @Override
    public String perform(HttpServletRequest request) throws LogicException, TechException {
        AbstractDao dao;
        if (request.getParameter("phase") == null) {
            dao = new DaoFactory().getDaoByName("quest");
            ArrayList<Quest> bothQuestsAnswers = dao.selectAllByParameter(request.getParameter("data"));

            request.setAttribute("testid", bothQuestsAnswers.get(0).getTestId());
            request.setAttribute("questnum", bothQuestsAnswers.size());
            request.setAttribute("olddata", request.getParameter("data"));
            request.setAttribute("data", bothQuestsAnswers);
            return "jsp/changetest.jsp";
        } else {

            dao = new DaoFactory().getDaoByName("quest");
            ArrayList<Quest> bothQuestsAnswers = dao.selectAllByParameter(request.getParameter("olddata"));
            AbstractDao adao = new DaoFactory().getDaoByName("answer");
            for (Quest quest : bothQuestsAnswers) {
                dao.updateByParameter(String.valueOf(quest.getId()), request.getParameter("quest" + quest.getId()));
                for (Answer answer : quest.getAnswers()) {
                    adao.updateByParameter(String.valueOf(answer.getId()), request.getParameter("answer" + answer.getId()));
                }
            }

            return "/jsp/postlog.jsp";
        }
    }

}
