/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @author Sergiusz
 */
public class ChooseTestCommand extends AbstractCommand {

    @Override
    public String perform(HttpServletRequest request) throws LogicException, TechException {
        new I18nDealer(this.getClass().getSimpleName()).assignLocale(request);
        AbstractDao dao = new DaoFactory().getDaoByName("quest");
        ArrayList<Quest> bothQuestsAnswers = dao.selectAllByParameter(request.getParameter("data"));

        request.getSession().setAttribute("result", null);
        if (!bothQuestsAnswers.isEmpty()) {
            request.setAttribute("testid", bothQuestsAnswers.get(0).getTestId());
            for(Quest quest: bothQuestsAnswers) {
                Collections.shuffle(quest.getAnswers());
            }
        }
        else {
            throw new LogicException("Test does not exist.");
        }
        request.setAttribute("data", bothQuestsAnswers);
        return flowPagePropertyHandler.getPropertyValue(this.getClass().getSimpleName());
    }

}
