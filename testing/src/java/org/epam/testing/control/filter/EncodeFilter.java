/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.control.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.log4j.Logger;
import org.epam.testing.exception.TechException;

/**
 * Фильтр применения необходимой кодировки.
 *
 * @author Sergiusz
 */
public class EncodeFilter implements Filter {

    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(EncodeFilter.class.getSimpleName());
    private String code;

    /**
     * Переопределённый метод от Filter. Инициализация фильтра.
     *
     * @param fConfig Конфигурация фильтра.
     */
    @Override
    public void init(FilterConfig fConfig) {
        code = fConfig.getInitParameter("encoding");
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
        try {
            String codeRequest = request.getCharacterEncoding();
            if (code != null && !code.equalsIgnoreCase(codeRequest)) {
                request.setCharacterEncoding(code);
                response.setCharacterEncoding(code);
            }
            chain.doFilter(request, response);

        } catch (UnsupportedEncodingException ex) {
            LOGGER.error(new TechException("Unsupported encoding in EncodeFilter.", ex.getCause()));
        } catch (IOException | ServletException ex) {
            LOGGER.error(new TechException("Trouble with doFilter in EncodeFilter.", ex.getCause()));
        }

    }

    /**
     * Переопределённый метод от Filter. Уничтожение объекта фильтра.
     */
    @Override
    public void destroy() {
        code = null;
    }
}
