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
        HashMap local = new HashMap();
        Object[] tempLocal = localePropertyHandler.getKeysSet().toArray();
        switch (commandName) {
            case "BeginCommand":
                for (Object key : tempLocal) {
                    if (key.toString().startsWith("A")
                            || key.toString().startsWith("B")) {
                        local.put(key.toString(), localePropertyHandler.getPropertyValue(key.toString()));
                    }
                }
                break;
            case "LoginCommand":
                local = new HashMap();
                for (Object key : tempLocal) {
                    if (key.toString().startsWith("A")
                            || key.toString().startsWith("B")
                            || key.toString().startsWith("C")) {
                        local.put(key.toString(), localePropertyHandler.getPropertyValue(key.toString()));
                    }
                }
                request.setAttribute("local", local);
                break;
            case "RegisterCommand":
                for (Object key : tempLocal) {
                    if (key.toString().startsWith("A")
                            || key.toString().startsWith("B")) {
                        local.put(key.toString(), localePropertyHandler.getPropertyValue(key.toString()));
                    }
                }
                break;
            case "NeedRegisterCommand":
                for (Object key : tempLocal) {
                    if (key.toString().startsWith("B")) {
                        local.put(key.toString(), localePropertyHandler.getPropertyValue(key.toString()));
                    }
                }
                break;
            case "NeedLoginCommand":
                for (Object key : tempLocal) {
                    if (key.toString().startsWith("A")) {
                        local.put(key.toString(), localePropertyHandler.getPropertyValue(key.toString()));
                    }
                }
                break;
            default:
                local = new HashMap();
                for (Object key : tempLocal) {
                    local.put(key.toString(), localePropertyHandler.getPropertyValue(key.toString()));
                }
        }
        request.setAttribute("local", local);
    }

}
