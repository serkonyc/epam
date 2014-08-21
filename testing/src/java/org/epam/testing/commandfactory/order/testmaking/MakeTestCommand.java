/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.testing.commandfactory.order.testmaking;

import javax.servlet.http.HttpServletRequest;
import org.epam.testing.commandfactory.order.AbstractCommand;
import org.epam.testing.daofactory.DaoFactory;
import org.epam.testing.daofactory.dao.AbstractDao;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;

/**
 *
 * @author Sergiusz
 */
public class MakeTestCommand extends AbstractCommand {

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
            adao.insertNew(
                    String.valueOf(lastIdQu),
                    request.getParameter(i + "-" + 1),
                    "true"
            );
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
