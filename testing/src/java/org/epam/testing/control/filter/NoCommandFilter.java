/*
 * Copyright (C) 2014 Sergiusz
 *
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
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.epam.testing.command.common.AbstractCommand;
import org.epam.testing.command.factory.CommandFactory;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;
import org.epam.testing.prophandler.PropertyHandler;
import org.epam.testing.utils.I18nDealer;

/**
 * Фильтр обработки запросов по адресу страницы без вызова команд.
 *
 * @author Sergiusz
 */
public class NoCommandFilter implements Filter {

    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(NoCommandFilter.class.getSimpleName());

    /**
     * Переопределённый метод от Filter. Инициализация фильтра.
     *
     * @param fConfig Конфигурация фильтра.
     */
    @Override
    public void init(FilterConfig fConfig) {
    }

    /**
     * Метод выполнения непосредственной работы и обработки фильтра.
     *
     * @param request Запрос JSP-страницы.
     * @param response Ответ отправки.
     * @param chain Объект для доступа к цепи обработки.
     */
    @Override
    public void doFilter(ServletRequest request,
            ServletResponse response, FilterChain chain) {
       
        DOMConfigurator.configure(request.getServletContext().getRealPath("\\logger\\log4j.xml"));
        PropertyHandler.setInput(request.getServletContext().getRealPath(""));
        try {
            HttpServletRequest filterRequest = (HttpServletRequest) request;
            HttpSession session = filterRequest.getSession(true);
            RequestDispatcher reqDispatch;
            if (session.getAttribute("jsppath") == null) {
                PropertyHandler.setInput(request.getServletContext().getRealPath(""));
                AbstractCommand cmd = CommandFactory.getCommandByName("login");
                reqDispatch = request.getRequestDispatcher(cmd.perform((HttpServletRequest) request));
                if (reqDispatch == null) {
                    reqDispatch = request.getRequestDispatcher("/jsp/error.jsp");
                }
            } else {
                reqDispatch = request.getRequestDispatcher(session.getAttribute("jsppath").toString());
                new I18nDealer("default").assignLocale((HttpServletRequest) request);
            }
            request.setAttribute("path", request.getServletContext().getContextPath());
            reqDispatch.forward(request, response);
            session.setAttribute("jsppath", null);

            chain.doFilter(request, response);

        } catch (LogicException | TechException ex) {
            LOGGER.error(ex);
        } catch (ServletException | IOException ex) {
            LOGGER.error(new TechException("Problem with servlet or input/output", ex.getCause()));
        }
    }

    /**
     * Переопределённый метод от Filter. Уничтожение объекта фильтра.
     */
    @Override
    public void destroy() {
    }
}
