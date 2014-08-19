/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.testing.control.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.epam.testing.commandfactory.CommandFactory;
import org.epam.testing.commandfactory.order.AbstractCommand;
import org.epam.testing.control.CommandController;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;
import org.epam.testing.prophandler.PropertyHandler;

/**
 *
 * @author Sergiusz
 */
public class NoCommandFilter implements Filter {

    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(NoCommandFilter.class.getSimpleName());

    @Override
    public void init(FilterConfig fConfig) {
    }

    @Override
    public void doFilter(ServletRequest request,
            ServletResponse response, FilterChain chain) {
        try {
            PropertyHandler.setInput(request.getServletContext().getRealPath(""));
            AbstractCommand cmd = CommandFactory.getCommandByName("login");
            RequestDispatcher reqDispatch = request.getRequestDispatcher(cmd.perform((HttpServletRequest) request));
            if (reqDispatch == null) {
                reqDispatch = request.getRequestDispatcher("/jsp/error.jsp");
            }
            request.setAttribute("path", request.getServletContext().getContextPath());
            reqDispatch.forward(request, response);

            chain.doFilter(request, response);

        } catch (LogicException | TechException ex) {
            LOGGER.error(ex);
        } catch (ServletException | IOException ex) {
            LOGGER.error(new TechException("Problem with servlet or input/output", ex.getCause()));
        }
    }

    @Override
    public void destroy() {
    }
}
