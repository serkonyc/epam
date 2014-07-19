/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.bursethread.exception;

/**
 *
 * @author Sergiusz
 */
public class TechException extends Exception {

    private static final String TYPE = "TECH: ";

    public TechException() {
        super();
    }

    public TechException(String message) {
        super(TYPE + message);
    }

    public TechException(Throwable cause) {
        super(cause);
    }

    public TechException(String message, Throwable cause) {
        super(TYPE + message, cause);
    }
}
