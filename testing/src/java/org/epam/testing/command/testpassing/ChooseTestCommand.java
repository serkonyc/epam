/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.command.testpassing;

import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import org.epam.testing.command.common.AbstractCommand;
import org.epam.testing.dao.factory.DaoFactory;
import org.epam.testing.dao.AbstractDao;
import org.epam.testing.dao.entity.Quest;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;
import org.epam.testing.utils.I18nDealer;

/**
 * Команда выбора теста для прохождения.
 *
 * @author Sergiusz
 */
public class ChooseTestCommand extends AbstractCommand {

    /**
     * Команда выбора теста для прохождения.
     *
     * @param request Запрос, переданный с jsp-страницы.
     * @return String-адрес страницы прохожения.
     * @throws LogicException в случае проблем с i18nDealer или DaoFactory.
     * @throws TechException в случае технических проблем.
     */
    @Override
    public String perform(HttpServletRequest request) throws LogicException, TechException {
        new I18nDealer(this.getClass().getSimpleName()).assignLocale(request);
        AbstractDao dao = new DaoFactory().getDaoByName("quest");
        ArrayList<Quest> bothQuestsAnswers = dao.selectAllByParameter(request.getParameter("data"));

        request.getSession().setAttribute("result", null);
        if (!bothQuestsAnswers.isEmpty()) {
            request.setAttribute("testid", bothQuestsAnswers.get(0).getTestId());
            for (Quest quest : bothQuestsAnswers) {
                Collections.shuffle(quest.getAnswers());
            }
        } else {
            throw new LogicException("Test does not exist.");
        }
        request.setAttribute("data", bothQuestsAnswers);
        return flowPagePropertyHandler.getPropertyValue(this.getClass().getSimpleName());
    }

}
