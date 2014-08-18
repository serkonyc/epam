/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.testing.commandfactory.order;

import javax.servlet.http.HttpServletRequest;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;

/**
 *
 * @author Sergiusz
 */
public class NeedLoginCommand extends AbstractCommand {

    @Override
    public String perform(HttpServletRequest request) throws LogicException, TechException {
        request.setAttribute("logorreg", "login");
        return "/jspview/welcome.jsp";
    }

}
