/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.testing.control;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.epam.testing.commandfactory.CommandFactory;
import org.epam.testing.commandfactory.order.AbstractCommand;
import org.epam.testing.dbconnection.DbaseConnectionPool;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;
import org.epam.testing.prophandler.PropertyHandler;

/**
 *
 * @author Sergiusz
 */
public class CommandController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(CommandController.class.getSimpleName());

    public CommandController() {
        super();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher reqDispatch = null;
        String errorMessage = null;
        String cmdPerformMessage = null;
        try {
            if (request.getParameter("command") != null) {
                AbstractCommand cmd = CommandFactory.getCommandByName(request.getParameter("command"));
                cmdPerformMessage = cmd.perform(request);
                reqDispatch = request.getRequestDispatcher(cmdPerformMessage);
            }

        } catch (LogicException | TechException exception) {
            errorMessage = exception.getMessage();
            LOGGER.error(exception);
            request.getSession().setAttribute("errorMess", exception.getMessage());
        } finally {
            try {
                if (errorMessage != null) {
                    request.getSession().setAttribute("jsppath", "jsp/error.jsp");
                    response.sendRedirect("/testing/error");
                }
                if (request.getParameter("progress") == null) {
                    if (reqDispatch != null) {
                        request.setAttribute("path", request.getContextPath());
                        reqDispatch.forward(request, response);
                    } else {
                        request.getSession().setAttribute("errorMess", errorMessage);
                        request.getSession().setAttribute("jsppath", "jsp/error.jsp");
                        response.sendRedirect("/testing/error");
                    }
                } else {                    
                    request.getSession().setAttribute("jsppath", cmdPerformMessage);
                    response.sendRedirect("/testing");
                }

            } catch (ServletException | IOException ex) {
                LOGGER.error(new TechException("Servlet exception with requestDispatcher", ex.getCause()));
                request.getSession().setAttribute("errorMess", ex.getMessage());
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    @Override
    public void init(ServletConfig config) {
        try {
            super.init(config);
            DOMConfigurator.configure(getServletContext().getRealPath("\\logger\\log4j.xml"));
            PropertyHandler.setInput(getServletContext().getRealPath(""));
        } catch (ServletException ex) {
            LOGGER.error(new TechException("Servlet exception with init!", ex.getCause()));
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            DbaseConnectionPool.getInstance().clearPool();
        } catch (TechException ex) {
            LOGGER.error(ex);
        }
    }

}
