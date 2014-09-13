/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.command.testmaking;

import javax.servlet.http.HttpServletRequest;
import org.epam.testing.command.common.AbstractCommand;
import org.epam.testing.dao.factory.DaoFactory;
import org.epam.testing.dao.AbstractDao;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;

/**
 * Команда окончательного создания нового теста.
 *
 * @author Sergiusz
 */
public class MakeTestCommand extends AbstractCommand {

    /**
     * Команда окончательного создания нового теста.
     *
     * @param request Запрос, переданный с jsp-страницы.
     * @return null для возвращения на главную.
     * @throws LogicException в случае проблем с DaoFactory.
     * @throws TechException в случае технических проблем.
     */
    @Override
    public String perform(HttpServletRequest request) throws LogicException, TechException {
        int questNum = Integer.parseInt(request.getParameter("questnum"));
        AbstractDao dao = new DaoFactory().getDaoByName("test");
        dao.insertNew(
                request.getSession().getAttribute("id").toString(),
                request.getParameter("inputid"),
                String.valueOf(questNum)
        );
        int lastId = dao.getLastId();
        dao = new DaoFactory().getDaoByName("quest");
        AbstractDao adao = new DaoFactory().getDaoByName("answer");
        for (int i = 1; i <= questNum; i++) {
            dao.insertNew(
                    String.valueOf(lastId),
                    request.getParameter("quest" + i)
            );
            int lastIdQu = dao.getLastId();
            if (!"".equals(request.getParameter(i + "-1.5"))) {
                adao.insertNew(
                        String.valueOf(lastIdQu),
                        request.getParameter(i + "-" + 1) + "!webtest!" + request.getParameter(i + "-1.5"),
                        "true"
                );
            } else {
                adao.insertNew(
                        String.valueOf(lastIdQu),
                        request.getParameter(i + "-" + 1),
                        "true"
                );
            }
            for (int j = 2; j <= 5; j++) {
                String answer = request.getParameter(i + "-" + j);
                if (answer == null) {
                    answer = "";
                }
                adao.insertNew(
                        String.valueOf(lastIdQu),
                        answer,
                        "false"
                );
            }
        }

        return null;
    }

}
