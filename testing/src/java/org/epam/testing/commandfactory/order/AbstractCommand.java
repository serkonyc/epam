/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.testing.commandfactory.order;

import javax.servlet.http.HttpServletRequest;
import org.epam.testing.control.CommandController;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;
import org.epam.testing.prophandler.PropertyHandler;

/**
 *
 * @author Sergiusz
 */
public abstract class AbstractCommand {

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(CommandController.class.getSimpleName());
    protected static PropertyHandler flowPagePropertyHandler;
    protected static PropertyHandler localePropertyHandler;

    static {
        try {
            flowPagePropertyHandler = new PropertyHandler("pageflow.properties");
        } catch (TechException ex) {
            LOGGER.error(ex);
        }
    }

    abstract public String perform(HttpServletRequest request) throws LogicException, TechException;

}
