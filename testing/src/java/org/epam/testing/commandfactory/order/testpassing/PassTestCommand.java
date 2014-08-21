/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.testing.commandfactory.order.testpassing;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.epam.testing.commandfactory.order.AbstractCommand;
import org.epam.testing.daofactory.DaoFactory;
import org.epam.testing.daofactory.dao.AbstractDao;
import org.epam.testing.daofactory.entity.Answer;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;
import org.epam.testing.utils.I18nDealer;

/**
 *
 * @author Sergiusz
 */
public class PassTestCommand extends AbstractCommand {

    @Override
    public String perform(HttpServletRequest request) throws LogicException, TechException {
        if (request.getSession().getAttribute("result") == null) {
            new I18nDealer(this.getClass().getSimpleName()).assignLocale(request);
            AbstractDao dao = new DaoFactory().getDaoByName("answer");
            ArrayList<Answer> answers = dao.selectAllByParameter(request.getParameter("testid"));
            float result = 1;
            for (Answer answer : answers) {
                if (request.getParameter(String.valueOf(answer.getQuestId())) != null) {
                    if (answer.getId() != Integer.parseInt(request.getParameter(String.valueOf(answer.getQuestId())))) {
                        result = result - 1.0f / answers.size();
                    }
                } else {
                    result = result - 1.0f / answers.size();
                }
            }
            dao = new DaoFactory().getDaoByName("passhistory");
            dao.insertNew(
                    request.getParameter("testid"),
                    request.getSession().getAttribute("id").toString(),
                    String.valueOf(result)
            );

            request.getSession().setAttribute("result",
                    "Your result of passing test is "
                    + String.format("%.2f", result * 100)
                    + "%");
            return flowPagePropertyHandler.getPropertyValue(this.getClass().getSimpleName());
        } else {
            request.getSession().setAttribute("result", null);
            return null;
        }
    }

}
