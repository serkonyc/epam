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
public class TechException extends Exception {

    public TechException() {
        super();
    }

    public TechException(String message) {
        super(message);
    }

    public TechException(Throwable cause) {
        super(cause);
    }

    public TechException(String message, Throwable cause) {
        super(message, cause);
    }
}
