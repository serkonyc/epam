/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.testing.command.testmanaging;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.epam.testing.command.common.AbstractCommand;
import org.epam.testing.dao.factory.DaoFactory;
import org.epam.testing.dao.AbstractDao;
import org.epam.testing.dao.entity.Answer;
import org.epam.testing.dao.entity.Quest;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;
import org.epam.testing.utils.I18nDealer;

/**
 *
 * @author Sergiusz
 */
public class ChangeChosenTestCommand extends AbstractCommand {

    @Override
    public String perform(HttpServletRequest request) throws LogicException, TechException {
        AbstractDao dao;
        new I18nDealer(this.getClass().getSimpleName()).assignLocale(request);
        if (request.getParameter("phase") == null) {
            dao = new DaoFactory().getDaoByName("quest");
            ArrayList<Quest> bothQuestsAnswers = dao.selectAllByParameter(request.getParameter("data"));

            request.setAttribute("testid", bothQuestsAnswers.get(0).getTestId());
            request.setAttribute("questnum", bothQuestsAnswers.size());
            request.setAttribute("olddata", request.getParameter("data"));
            request.setAttribute("data", bothQuestsAnswers);
            return flowPagePropertyHandler.getPropertyValue(this.getClass().getSimpleName());
        } else {
            dao = new DaoFactory().getDaoByName("quest");
            ArrayList<Quest> bothQuestsAnswers = dao.selectAllByParameter(request.getParameter("olddata"));
            AbstractDao adao = new DaoFactory().getDaoByName("answer");
            for (Quest quest : bothQuestsAnswers) {
                dao.updateByParameter(String.valueOf(quest.getId()), request.getParameter("quest" + quest.getId()));
                for (Answer answer : quest.getAnswers()) {
                    if (request.getParameter("answer" + answer.getId() + "-1.5") == null
                            || "".equals(request.getParameter("answer" + answer.getId() + "-1.5"))) {
                        adao.updateByParameter(String.valueOf(answer.getId()), request.getParameter("answer" + answer.getId()));
                    } else {
                        adao.updateByParameter(String.valueOf(answer.getId()),
                                request.getParameter("answer" + answer.getId())
                                + "!webtest!"
                                + request.getParameter("answer" + answer.getId() + "-1.5"));
                    }
                }
            }

            return null;
        }
    }

}
