/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.testing.exception;

/**
 *
 * @author Sergiusz
 */
public class LogicException extends Exception {

    public LogicException() {
        super();
    }

    public LogicException(String message) {
        super(message);
    }

    public LogicException(Throwable exception) {
        super(exception);
    }

    public LogicException(String message, Throwable exception) {
        super(message, exception);
    }

}
