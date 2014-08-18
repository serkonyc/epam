/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.testing.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.epam.testing.control.CommandController;
import org.epam.testing.exception.TechException;
import org.epam.testing.prophandler.PropertyHandler;

/**
 *
 * @author Sergiusz
 */
public class DbaseConnectionPool {

    private static final Logger LOGGER = CommandController.LOGGER;
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
            if (instance == null) {
                instance = new DbaseConnectionPool();
            }
            return instance;
        }

    }

    public static DbaseConnectionPool getInstance() throws TechException {
        return PoolHolder.getInstance();
    }

    public final Connection getConnection() throws TechException {
        try {
            Connection connection = allConnections.take();
            usedConnections.add(connection);
            return connection;
        } catch (InterruptedException ex) {
            throw new TechException("Take connection trouble. ", ex.getCause());
        }
    }

    public final void releaseConnection(Connection connection) throws TechException {
        try {
            allConnections.put(connection);
            usedConnections.remove(connection);
        } catch (InterruptedException ex) {
            throw new TechException("Put connection trouble. ", ex.getCause());
        }
    }

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
