/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.exception;

/**
 * Класс логических исключений.
 *
 * @author Sergiusz
 */
public class LogicException extends Exception {

    /**
     * Конструктор неинформативного исключения.
     */
    public LogicException() {
        super();
    }

    /**
     * Конструктор исключения с сообщением.
     *
     * @param message Сообщение от инициирующего исключения или определённого
     * иным путём.
     */
    public LogicException(String message) {
        super(message);
    }

    /**
     * Конструктор исключения на основе уже имеющегося или перехваченного.
     *
     * @param exception Объект инициирующего исключения.
     */
    public LogicException(Throwable exception) {
        super(exception);
    }

    /**
     * Конструктор исключения и сообщением и на основе перехваченного.
     *
     * @param message Сообщение об ошибке.
     * @param exception Объект инициирующего исключения.
     */
    public LogicException(String message, Throwable exception) {
        super(message, exception);
    }

}
