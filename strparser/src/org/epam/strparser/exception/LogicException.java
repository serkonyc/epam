/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.strparser.exception;

/**
 *
 * @author Sergiusz
 */
public class LogicException extends Exception {

    private static final String TYPE = "LOGIC: ";

    public LogicException() {
        super();
    }

    public LogicException(String message) {
        super(TYPE + message);
    }

    public LogicException(Throwable exception) {
        super(exception);
    }

    public LogicException(String message, Throwable exception) {
        super(TYPE + message, exception);
    }

}
