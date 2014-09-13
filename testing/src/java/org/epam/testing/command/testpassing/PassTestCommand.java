/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.command.testpassing;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.epam.testing.command.common.AbstractCommand;
import org.epam.testing.dao.factory.DaoFactory;
import org.epam.testing.dao.AbstractDao;
import org.epam.testing.dao.entity.Answer;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;
import org.epam.testing.utils.I18nDealer;

/**
 * Команда завершения теста.
 *
 * @author Sergiusz
 */
public class PassTestCommand extends AbstractCommand {

    /**
     * Команда завершения теста.
     *
     * @param request Запрос, переданный с jsp-страницы.
     * @return String-адрес с результатами либо null для перехода на главную.
     * @throws LogicException в случае проблем с i18nDealer или DaoFactory.
     * @throws TechException в случае технических проблем.
     */
    @Override
    public String perform(HttpServletRequest request) throws LogicException, TechException {
        if (request.getSession().getAttribute("result") == null) {
            new I18nDealer(this.getClass().getSimpleName()).assignLocale(request);
            AbstractDao dao = new DaoFactory().getDaoByName("answer");
            ArrayList<Answer> answers = dao.selectAllByParameter(request.getParameter("testid"));
            float result = 1;
            for (Answer answer : answers) {
                if (request.getParameter(String.valueOf(answer.getQuestId())) != null) {
                    if (answer.getTextAdv() == null) {
                        if (answer.getId() != Integer.parseInt(request.getParameter(String.valueOf(answer.getQuestId())))) {
                            result = result - 1.0f / answers.size();
                        }
                    } else {
                        if (request.getParameter(String.valueOf(answer.getQuestId()) + "Adv") == null
                                || answer.getId() != Integer.parseInt(request.getParameter(String.valueOf(answer.getQuestId())))
                                || answer.getId() != Integer.parseInt(request.getParameter(String.valueOf(answer.getQuestId()) + "Adv"))) {
                            result = result - 1.0f / answers.size();
                        }
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
                    String.format("%.2f", result * 100) + "%");
            return flowPagePropertyHandler.getPropertyValue(this.getClass().getSimpleName());
        } else {
            request.getSession().setAttribute("result", null);
            return null;
        }
    }

}
