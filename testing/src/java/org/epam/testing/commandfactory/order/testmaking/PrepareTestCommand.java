/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.testing.commandfactory.order.testmaking;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.epam.testing.commandfactory.order.AbstractCommand;
import org.epam.testing.daofactory.DaoFactory;
import org.epam.testing.daofactory.dao.AbstractDao;
import org.epam.testing.daofactory.entity.Subject;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;

/**
 *
 * @author Sergiusz
 */
public class PrepareTestCommand extends AbstractCommand {

    @Override
    public String perform(HttpServletRequest request) throws LogicException, TechException {
        AbstractDao dao = new DaoFactory().getDaoByName("subject");
        ArrayList<Subject> subjects = dao.selectAll();
        
        request.setAttribute("wascommand", "prepare");
        request.setAttribute("subjs", subjects);
        return "/jspview/addtest.jsp";
    }

}
