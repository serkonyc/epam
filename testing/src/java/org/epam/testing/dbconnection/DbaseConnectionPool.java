/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import org.epam.testing.exception.TechException;
import org.epam.testing.prophandler.PropertyHandler;

/**
 * Пул соединений для связи с базой данных.
 *
 * @author Sergiusz
 */
public class DbaseConnectionPool {

    private final int POOL_LIMIT;
    private LinkedBlockingQueue<Connection> allConnections;
    private LinkedBlockingQueue<Connection> usedConnections;
    private PropertyHandler propertyHandler;

    private DbaseConnectionPool() throws TechException {
        propertyHandler = new PropertyHandler("database.properties");
        POOL_LIMIT = (int) Double.parseDouble(propertyHandler.getPropertyValue("limit"));
        allConnections = new LinkedBlockingQueue<>(POOL_LIMIT);
        usedConnections = new LinkedBlockingQueue<>(POOL_LIMIT);
        for (int i = 0; i < POOL_LIMIT; i++) {
            try {
                Connection connection = DriverManager.getConnection(
                        propertyHandler.getPropertyValue("url"),
                        propertyHandler.getPropertyValue("username"),
                        propertyHandler.getPropertyValue("password")
                );
                allConnections.add(connection);

            } catch (SQLException ex) {
                throw new TechException("DriverManager error. ", ex.getCause());
            }

        }
    }

    private static class PoolHolder {

        private static DbaseConnectionPool instance = null;

        private static DbaseConnectionPool getInstance() throws TechException {

            DbaseConnectionPool localInstance = instance;
            if (localInstance == null) {
                synchronized (DbaseConnectionPool.class) {
                    localInstance = instance;
                    if (localInstance == null) {
                        instance = localInstance = new DbaseConnectionPool();
                    }
                }
            }
            return localInstance;
        }

    }

    /**
     * Метод получения единого экземпляра класса пула.
     *
     * @return @throws TechException в случае любых технических проблем.
     */
    public static DbaseConnectionPool getInstance() throws TechException {
        return PoolHolder.getInstance();
    }

    /**
     * Метод получения соединения и его перемещения в список используемых.
     *
     * @return @throws TechException в случае любых технических проблем.
     */
    public final Connection getConnection() throws TechException {
        try {
            Connection connection = allConnections.take();
            usedConnections.add(connection);
            return connection;
        } catch (InterruptedException ex) {
            throw new TechException("Take connection trouble. ", ex.getCause());
        }
    }

    /**
     * Метод освобождения соединения и возвращения его в общий список.
     *
     * @param connection Соединение, отданное пользователем обратно в пул.
     * @throws TechException в случае любых технических проблем.
     */
    public final void releaseConnection(Connection connection) throws TechException {
        try {
            allConnections.put(connection);
            usedConnections.remove(connection);
        } catch (InterruptedException ex) {
            throw new TechException("Put connection trouble. ", ex.getCause());
        }
    }

    /**
     * Метод полной очистки пула от соединений.
     *
     * @throws TechException в случае любых технических проблем.
     */
    public final void clearPool() throws TechException {
        Iterator<Connection> iterator = allConnections.iterator();
        while (iterator.hasNext()) {
            try {
                Connection connection = iterator.next();
                connection.close();
                iterator.remove();
            } catch (SQLException ex) {
                throw new TechException("Clearing pool SQL trouble!", ex.getCause());
            }
        }
    }

}
