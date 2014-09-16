/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.command.statistics;

import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import org.epam.testing.command.common.AbstractCommand;
import org.epam.testing.dao.AbstractDao;
import org.epam.testing.dao.entity.Test;
import org.epam.testing.dao.factory.DaoFactory;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;
import org.epam.testing.utils.ByPageReflector;
import org.epam.testing.utils.I18nDealer;

/**
 * Команда вывода таблицы статистики созданий тестов.
 * 
 * @author Sergiusz
 */
public class LookMakeStatCommand extends AbstractCommand {

    /**
     * Команда вывода таблицы статистики созданий тестов.
     * 
     * @param request Запрос, переданный с jsp-страницы.
     * @return адрес страницы с содержимым или null в случае перехода на главную.
     * @throws LogicException в случае проблем с i18nDealer.
     * @throws TechException в случае технических проблем.
     */
    @Override
    public String perform(HttpServletRequest request) throws LogicException, TechException {
        new I18nDealer(this.getClass().getSimpleName()).assignLocale(request);
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
            Collections.reverse(posttestlist);
            for (int i = 0; i < 10; i++) {
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
            return flowPagePropertyHandler.getPropertyValue(this.getClass().getSimpleName());
        } else {
            return null;
        }
    }
}
