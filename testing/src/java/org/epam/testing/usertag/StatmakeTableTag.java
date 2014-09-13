/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.usertag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import org.epam.testing.dao.entity.Test;

/**
 * Класс пользовательского тега для отображения таблицы статистики созданий
 * тестов
 *
 * @author Sergiusz
 */
public class StatmakeTableTag extends TagSupport {

    private ArrayList<Test> testdata;
    private int count, rows;
    private HashMap local;

    /**
     * Сеттер для локали.
     *
     * @param local Параметр настроек языка.
     */
    public void setLocal(HashMap local) {
        this.local = local;
    }

    /**
     * Сеттер для отображаемых данных.
     *
     * @param testdata Список тестов.
     */
    public void setTestdata(ArrayList<Test> testdata) {
        this.testdata = testdata;
    }

    /**
     * Сеттер количества выведенных строк.
     *
     * @param rows Число строк (10).
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * Переопределённый метод вывода при инициализации тега.
     *
     * @return число из интерфейса Tag
     * @throws JspTagException в случае технической проблемы.
     */
    @Override
    public int doStartTag() throws JspTagException {
        try {
            count = 0;
            JspWriter out = pageContext.getOut();
            out.write("<table class=\"contentPart\">");
            out.write(
                    "<tr><td>" + local.get("FDtestnumber")
                    + "</td><td>" + local.get("FAsubject")
                    + "</td><td>" + local.get("FBtheme")
                    + "</td></tr>");
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return EVAL_BODY_INCLUDE;
    }

    /**
     * Переопределённый метод вывода после очередного элемента данных.
     *
     * @return число из интерфейса Tag
     * @throws JspTagException в случае технической проблемы.
     */
    @Override
    public int doAfterBody() throws JspTagException {
        if (count < rows) {
            try {
                if (testdata.get(count).getTheme() != null) {
                    pageContext.getOut().write("<tr><td>&nbsp;&nbsp;&nbsp;"
                            + testdata.get(count).getId()
                            + "</td><td>&nbsp;&nbsp;&nbsp;"
                            + testdata.get(count).getTheme().getSubj().getName()
                            + "</td><td>&nbsp;&nbsp;&nbsp;"
                            + testdata.get(count).getTheme().getName()
                            + "</td></tr>");
                }
            } catch (IOException e) {
                throw new JspTagException(e.getMessage());
            }
            count++;
            return EVAL_BODY_AGAIN;
        } else {
            return SKIP_BODY;
        }
    }

    /**
     * Переопределённый метод вывода при окончании выполнения тега.
     *
     * @return число из интерфейса Tag
     * @throws JspTagException в случае технической проблемы.
     */
    @Override
    public int doEndTag() throws JspTagException {
        try {
            pageContext.getOut().write("</table>");
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        count = 0;
        return EVAL_PAGE;
    }
}
