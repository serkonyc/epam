/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.testing.utils;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;
import org.epam.testing.prophandler.PropertyHandler;

/**
 *
 * @author Sergiusz
 */
public class I18nDealer {

    private String commandName;
    private PropertyHandler localePropertyHandler;

    public I18nDealer(String commandName) {
        this.commandName = commandName;
    }

    public void assignLocale(HttpServletRequest request) throws TechException, LogicException {
        if (request.getSession(true).getAttribute("lang") == null) {
            request.getSession().setAttribute("lang", "label_en");
        }
        localePropertyHandler = new PropertyHandler(
                request.getSession()
                .getAttribute("lang")
                .toString()
                .concat(".properties")
        );
        HashMap local;
        switch (commandName) {
            case "BeginCommand":
                local = new HashMap();
                local.put("authnickplaceholder", localePropertyHandler.getPropertyValue("authnickplaceholder"));
                local.put("authpasswordplaceholder", localePropertyHandler.getPropertyValue("authpasswordplaceholder"));
                local.put("authnickplaceholdererror", localePropertyHandler.getPropertyValue("authnickplaceholdererror"));
                local.put("passwordplaceholdererror", localePropertyHandler.getPropertyValue("passwordplaceholdererror"));
                local.put("passwordrepeatplaceholder", localePropertyHandler.getPropertyValue("passwordrepeatplaceholder"));
                local.put("authsignin", localePropertyHandler.getPropertyValue("authsignin"));
                local.put("authsignup", localePropertyHandler.getPropertyValue("authsignup"));
                local.put("regnickplaceholder", localePropertyHandler.getPropertyValue("regnickplaceholder"));
                local.put("regpasswordplaceholder", localePropertyHandler.getPropertyValue("regpasswordplaceholder"));
                local.put("regnickplaceholdererror", localePropertyHandler.getPropertyValue("regnickplaceholdererror"));
                local.put("regsignin", localePropertyHandler.getPropertyValue("regsignin"));
                local.put("regsignup", localePropertyHandler.getPropertyValue("regsignup"));
                local.put("emailplaceholder", localePropertyHandler.getPropertyValue("emailplaceholder"));
                request.setAttribute("local", local);
                break;
            case "LoginCommand":
                local = new HashMap();
                local.put("authnickplaceholder", localePropertyHandler.getPropertyValue("authnickplaceholder"));
                local.put("authpasswordplaceholder", localePropertyHandler.getPropertyValue("authpasswordplaceholder"));
                local.put("authnickplaceholdererror", localePropertyHandler.getPropertyValue("authnickplaceholdererror"));
                local.put("passwordplaceholdererror", localePropertyHandler.getPropertyValue("passwordplaceholdererror"));
                local.put("passwordrepeatplaceholder", localePropertyHandler.getPropertyValue("passwordrepeatplaceholder"));
                local.put("authsignin", localePropertyHandler.getPropertyValue("authsignin"));
                local.put("authsignup", localePropertyHandler.getPropertyValue("authsignup"));
                local.put("regnickplaceholder", localePropertyHandler.getPropertyValue("regnickplaceholder"));
                local.put("regpasswordplaceholder", localePropertyHandler.getPropertyValue("regpasswordplaceholder"));
                local.put("regnickplaceholdererror", localePropertyHandler.getPropertyValue("regnickplaceholdererror"));
                local.put("regsignin", localePropertyHandler.getPropertyValue("regsignin"));
                local.put("regsignup", localePropertyHandler.getPropertyValue("regsignup"));
                local.put("emailplaceholder", localePropertyHandler.getPropertyValue("emailplaceholder"));

                local.put("testlabel", localePropertyHandler.getPropertyValue("testlabel"));
                local.put("addtest", localePropertyHandler.getPropertyValue("addtest"));
                local.put("passtest", localePropertyHandler.getPropertyValue("passtest"));
                local.put("changetest", localePropertyHandler.getPropertyValue("changetest"));
                local.put("deletetest", localePropertyHandler.getPropertyValue("deletetest"));
                local.put("statpass", localePropertyHandler.getPropertyValue("statpass"));
                local.put("statmake", localePropertyHandler.getPropertyValue("statmake"));
                local.put("goaway", localePropertyHandler.getPropertyValue("goaway"));
                request.setAttribute("local", local);
                break;
            case "RegisterCommand":
                local = new HashMap();
                local.put("authnickplaceholder", localePropertyHandler.getPropertyValue("authnickplaceholder"));
                local.put("authpasswordplaceholder", localePropertyHandler.getPropertyValue("authpasswordplaceholder"));
                local.put("authnickplaceholdererror", localePropertyHandler.getPropertyValue("authnickplaceholdererror"));
                local.put("passwordplaceholdererror", localePropertyHandler.getPropertyValue("passwordplaceholdererror"));
                local.put("passwordrepeatplaceholder", localePropertyHandler.getPropertyValue("passwordrepeatplaceholder"));
                local.put("authsignin", localePropertyHandler.getPropertyValue("authsignin"));
                local.put("authsignup", localePropertyHandler.getPropertyValue("authsignup"));
                local.put("regnickplaceholder", localePropertyHandler.getPropertyValue("regnickplaceholder"));
                local.put("regpasswordplaceholder", localePropertyHandler.getPropertyValue("regpasswordplaceholder"));
                local.put("regnickplaceholdererror", localePropertyHandler.getPropertyValue("regnickplaceholdererror"));
                local.put("regsignin", localePropertyHandler.getPropertyValue("regsignin"));
                local.put("regsignup", localePropertyHandler.getPropertyValue("regsignup"));
                local.put("emailplaceholder", localePropertyHandler.getPropertyValue("emailplaceholder"));
                request.setAttribute("local", local);
                break;
            case "NeedRegisterCommand":
                local = new HashMap();
                local.put("regnickplaceholder", localePropertyHandler.getPropertyValue("regnickplaceholder"));
                local.put("emailplaceholder", localePropertyHandler.getPropertyValue("emailplaceholder"));
                local.put("regpasswordplaceholder", localePropertyHandler.getPropertyValue("regpasswordplaceholder"));
                local.put("regnickplaceholdererror", localePropertyHandler.getPropertyValue("regnickplaceholdererror"));
                local.put("passwordrepeatplaceholder", localePropertyHandler.getPropertyValue("passwordrepeatplaceholder"));
                local.put("regsignin", localePropertyHandler.getPropertyValue("regsignin"));
                local.put("regsignup", localePropertyHandler.getPropertyValue("regsignup"));
                request.setAttribute("local", local);
                break;
            case "NeedLoginCommand":
                local = new HashMap();
                local.put("authnickplaceholder", localePropertyHandler.getPropertyValue("authnickplaceholder"));
                local.put("authpasswordplaceholder", localePropertyHandler.getPropertyValue("authpasswordplaceholder"));
                local.put("authnickplaceholdererror", localePropertyHandler.getPropertyValue("authnickplaceholdererror"));
                local.put("passwordrepeatplaceholder", localePropertyHandler.getPropertyValue("passwordrepeatplaceholder"));
                local.put("authsignin", localePropertyHandler.getPropertyValue("authsignin"));
                local.put("authsignup", localePropertyHandler.getPropertyValue("authsignup"));
                request.setAttribute("local", local);
                break;
            default:
                local = new HashMap();
                local.put("authnickplaceholder", localePropertyHandler.getPropertyValue("authnickplaceholder"));
                local.put("authpasswordplaceholder", localePropertyHandler.getPropertyValue("authpasswordplaceholder"));
                local.put("authnickplaceholdererror", localePropertyHandler.getPropertyValue("authnickplaceholdererror"));
                local.put("passwordplaceholdererror", localePropertyHandler.getPropertyValue("passwordplaceholdererror"));
                local.put("passwordrepeatplaceholder", localePropertyHandler.getPropertyValue("passwordrepeatplaceholder"));
                local.put("authsignin", localePropertyHandler.getPropertyValue("authsignin"));
                local.put("authsignup", localePropertyHandler.getPropertyValue("authsignup"));
                local.put("regnickplaceholder", localePropertyHandler.getPropertyValue("regnickplaceholder"));
                local.put("regpasswordplaceholder", localePropertyHandler.getPropertyValue("regpasswordplaceholder"));
                local.put("regnickplaceholdererror", localePropertyHandler.getPropertyValue("regnickplaceholdererror"));
                local.put("regsignin", localePropertyHandler.getPropertyValue("regsignin"));
                local.put("regsignup", localePropertyHandler.getPropertyValue("regsignup"));
                local.put("emailplaceholder", localePropertyHandler.getPropertyValue("emailplaceholder"));

                local.put("testlabel", localePropertyHandler.getPropertyValue("testlabel"));
                local.put("addtest", localePropertyHandler.getPropertyValue("addtest"));
                local.put("passtest", localePropertyHandler.getPropertyValue("passtest"));
                local.put("changetest", localePropertyHandler.getPropertyValue("changetest"));
                local.put("deletetest", localePropertyHandler.getPropertyValue("deletetest"));
                local.put("statpass", localePropertyHandler.getPropertyValue("statpass"));
                local.put("statmake", localePropertyHandler.getPropertyValue("statmake"));
                local.put("goaway", localePropertyHandler.getPropertyValue("goaway"));
                
                local.put("add", localePropertyHandler.getPropertyValue("add"));
                local.put("subchoose", localePropertyHandler.getPropertyValue("subchoose"));
                local.put("chooseplaceholder", localePropertyHandler.getPropertyValue("chooseplaceholder"));
                
                local.put("themechoose", localePropertyHandler.getPropertyValue("themechoose"));
                local.put("questnumber", localePropertyHandler.getPropertyValue("questnumber"));
                local.put("3", localePropertyHandler.getPropertyValue("3"));
                local.put("4", localePropertyHandler.getPropertyValue("4"));
                local.put("5", localePropertyHandler.getPropertyValue("5"));
                local.put("6", localePropertyHandler.getPropertyValue("6"));
                local.put("7", localePropertyHandler.getPropertyValue("7"));
                local.put("8", localePropertyHandler.getPropertyValue("8"));
                local.put("9", localePropertyHandler.getPropertyValue("9"));
                local.put("10", localePropertyHandler.getPropertyValue("10"));

                
               // local.put("subchoose", localePropertyHandler.getPropertyValue("subchoose"));
               // local.put("subchooseplaceholder", localePropertyHandler.getPropertyValue("subchooseplaceholder"));
                request.setAttribute("local", local);
            //throw new LogicException("There is no such action");
        }
    }

}
