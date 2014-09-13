/*
 * Copyright (C) 2014 Sergiusz
 *
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
 * Команда окончательного изменения выбранного теста.
 *
 * @author Sergiusz
 */
public class ChangeChosenTestCommand extends AbstractCommand {

    /**
     * Команда окончательного изменения выбранного теста.
     *
     * @param request Запрос, переданный с jsp-страницы.
     * @return адрес страницы с формой изменения либо null для возвращения на
     * главную.
     * @throws LogicException в случае проблем с i18nDealer или DaoFactory.
     * @throws TechException в случае технических проблем.
     */
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
