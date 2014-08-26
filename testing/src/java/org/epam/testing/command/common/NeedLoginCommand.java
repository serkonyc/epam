/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.testing.command.common;

import javax.servlet.http.HttpServletRequest;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;
import org.epam.testing.utils.I18nDealer;

/**
 *
 * @author Sergiusz
 */
public class NeedLoginCommand extends AbstractCommand {

    @Override
    public String perform(HttpServletRequest request) throws LogicException, TechException {
        request.setAttribute("logorreg", "login");
        new I18nDealer(this.getClass().getSimpleName()).assignLocale(request);
        return flowPagePropertyHandler.getPropertyValue(this.getClass().getSimpleName());
    }

}
