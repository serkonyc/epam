/*
 * Copyright (C) 2014 Sergiusz
 *
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
import org.epam.testing.command.factory.CommandFactory;
import org.epam.testing.command.common.AbstractCommand;
import org.epam.testing.dbconnection.DbaseConnectionPool;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;

/**
 * Сервлет, обрабатывающий запросы с JSP и выполняющий соответствующие команды.
 *
 * @author Sergiusz
 */
public class CommandController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(CommandController.class.getSimpleName());

    /**
     * Конструктор класса, наследующего HttpServlet
     */
    public CommandController() {
        super();
    }

    /**
     * Метод обработки запросов, назначающий выполнение необходимых команд.
     *
     * @param request Запрос с jsp-страницы.
     * @param response Ответ отправки.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher reqDispatch = null;
        String errorMessage = null;
        String cmdPerformMessage = null;
        try {
            if (request.getParameter("command") != null
                    && (request.getSession().getAttribute("id") != null
                    || request.getRequestURI().endsWith("enter"))
                    || request.getSession().getAttribute("needfwd") != null) {
                AbstractCommand cmd;
                if (request.getParameter("command") != null) {
                    cmd = CommandFactory.getCommandByName(request.getParameter("command"));
                    request.getSession().setAttribute("needfwd", null);
                } else {
                    cmd = CommandFactory.getCommandByName(request.getSession().getAttribute("needfwd").toString());
                }
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
                } else if (cmdPerformMessage == null) {
                    request.getSession().setAttribute("jsppath", null);
                    response.sendRedirect("/testing");
                } else if (request.getParameter("progress") == null && reqDispatch != null) {
                    request.setAttribute("path", request.getContextPath());
                    reqDispatch.forward(request, response);
                } else if (request.getAttribute("smartredir") != null) {
                    request.getSession().setAttribute("needfwd", request.getAttribute("smartredir"));
                    response.sendRedirect("/testing/" + request.getAttribute("smartredir"));
                } else {
                    request.getSession().setAttribute("jsppath", cmdPerformMessage);
                    response.sendRedirect("/testing");
                }

            } catch (ServletException | IOException ex) {
                try {
                    LOGGER.error(new TechException("Servlet exception with requestDispatcher", ex.getCause()));
                    response.sendRedirect("/testing/error");
                } catch (IOException exep) {
                    LOGGER.error(new TechException("Servlet exception with requestDispatcher", exep.getCause()));
                }
            }
        }
    }

    /**
     * Переопределённый метод от HttpServlet
     *
     * @param request Запрос с jsp-страницы.
     * @param response Ответ отправки.
     * @throws ServletException в случае проблем внутри сервлета.
     * @throws IOException в случае ошибок ввода/вывода.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Переопределённый метод от HttpServlet
     *
     * @param request Запрос с jsp-страницы.
     * @param response Ответ отправки.
     * @throws ServletException в случае проблем внутри сервлета.
     * @throws IOException в случае ошибок ввода/вывода.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Переопределённый метод от HttpServlet
     *
     * @return String-описание сервлета.
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

    /**
     * Переопределённый метод от HttpServlet. Инициализация сервлета.
     *
     * @param config Объект конфигурации сервлета.
     */
    @Override
    public void init(ServletConfig config) {
    }

    /**
     * Переопределённый метод от HttpServlet. Уничтожение сервлета, очищение
     * пула соединений.
     */
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
