/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.exception;

/**
 * Класс технических исключений.
 *
 * @author Sergiusz
 */
public class TechException extends Exception {

    /**
     * Конструктор неинформативного исключения.
     */
    public TechException() {
        super();
    }

    /**
     * Конструктор исключения с сообщением.
     *
     * @param message Сообщение от инициирующего исключения или определённого
     * иным путём.
     */
    public TechException(String message) {
        super(message);
    }

    /**
     * Конструктор исключения на основе уже имеющегося или перехваченного.
     *
     * @param cause Объект инициирующего исключения.
     */
    public TechException(Throwable cause) {
        super(cause);
    }

    /**
     * Конструктор исключения и сообщением и на основе перехваченного.
     *
     * @param message Сообщение об ошибке.
     * @param cause Объект инициирующего исключения.
     */
    public TechException(String message, Throwable cause) {
        super(message, cause);
    }
}
