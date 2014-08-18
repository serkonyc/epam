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

    public static final Logger LOGGER = Logger.getLogger("console-file");

    public CommandController() {
        super();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher reqDispatch = null;
        try {
            if (request.getParameter("command") != null) {
                AbstractCommand cmd = CommandFactory.getCommandByName(request.getParameter("command"));
                reqDispatch = request.getRequestDispatcher(cmd.perform(request));
            } else {
                AbstractCommand cmd = CommandFactory.getCommandByName("login");
                reqDispatch = request.getRequestDispatcher(cmd.perform(request));
            }

        } catch (LogicException | TechException exception) {
            request.setAttribute("errorMess", exception.getMessage());
            reqDispatch = request.getRequestDispatcher("/jspview/error.jsp");
            LOGGER.error(exception);

        } finally {
            try {
                if (reqDispatch == null) {
                    reqDispatch = request.getRequestDispatcher("/jspview/error.jsp");
                }
                request.setAttribute("path", request.getContextPath());
                reqDispatch.forward(request, response);

            } catch (ServletException | IOException ex) {
                LOGGER.error(new TechException("Servlet exception with requestDispatcher", ex.getCause()));
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
