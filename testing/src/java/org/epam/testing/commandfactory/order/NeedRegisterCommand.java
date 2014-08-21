/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.testing.commandfactory.order;

import javax.servlet.http.HttpServletRequest;
import static org.epam.testing.commandfactory.order.AbstractCommand.localePropertyHandler;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;
import org.epam.testing.prophandler.PropertyHandler;
import org.epam.testing.utils.I18nDealer;

/**
 *
 * @author Sergiusz
 */
public class NeedRegisterCommand extends AbstractCommand {

    @Override
    public String perform(HttpServletRequest request) throws LogicException, TechException {
        request.setAttribute("logorreg", "register");
        new I18nDealer(this.getClass().getSimpleName()).assignLocale(request);
        return flowPagePropertyHandler.getPropertyValue(this.getClass().getSimpleName());
    }

}
