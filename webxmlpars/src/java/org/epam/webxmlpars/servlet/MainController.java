/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.webxmlpars.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.epam.xmlparser.buildfactory.WeaponBuildFactory;
import org.epam.xmlparser.buildfactory.builder.AbstractWeapBuilder;
import org.epam.xmlparser.exception.TechException;

/**
 *
 * @author Sergiusz
 */
public class MainController extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger("console-file");

    public MainController() {
        super();
        DOMConfigurator.configure("log4j.xml");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        String paramPage = request.getParameter("command");
        if (paramPage != null && !paramPage.isEmpty()) {
            try {
                
                AbstractWeapBuilder builder = new WeaponBuildFactory().createWeaponBuilder(paramPage);
                builder.buildWeaponArrays(request.getServletContext().getRealPath("\\input\\weapons.xml"));
                request.setAttribute("weapons", builder.getWeapons());
                request.setAttribute("type", paramPage);
                request.setAttribute("path", request.getContextPath());
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/output.jsp");
                requestDispatcher.forward(request, response);

            } catch (ServletException ex) {
                LOGGER.error(new TechException("Servlet exception with requestDispatcher", ex));
            } catch (IOException ex) {
                LOGGER.error(new TechException("I/O exception with requestDispatcher", ex));
            } catch (TechException ex) {
                LOGGER.error(ex);
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
}
