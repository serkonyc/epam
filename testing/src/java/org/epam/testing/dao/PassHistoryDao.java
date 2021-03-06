/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.epam.testing.dao.entity.PassHistory;
import org.epam.testing.dbconnection.DbaseConnectionPool;
import org.epam.testing.exception.TechException;

/**
 * Класс работы с сущностями истории прохождения.
 *
 * @author Sergiusz
 */
public class PassHistoryDao extends AbstractDao<PassHistory> {

    private final String SELECT_ALL = "SELECT * FROM passhistory";
    private final String INSERT_ONE = "INSERT INTO passhistory(test_id, user_id, result) VALUES (?,?,?)";
    private final String SELECT_ALL_BY_USERID = "SELECT * FROM passhistory WHERE passhistory.user_id = (?)";

    /**
     * Метод получения полной выборки из базы данных.
     *
     * @return ArrayList всех элементов.
     * @throws TechException в случае проблем с доступом к БД.
     */
    @Override
    public ArrayList selectAll() throws TechException {
        Connection connection = DbaseConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                returnList.add(
                        new PassHistory(
                                Float.parseFloat(resultSet.getString("passhistory.result")),
                                Integer.parseInt(resultSet.getString("passhistory.id")),
                                Integer.parseInt(resultSet.getString("passhistory.test_id")),
                                Integer.parseInt(resultSet.getString("passhistory.user_id"))
                        )
                );
            }
            return returnList;

        } catch (SQLException ex) {
            throw new TechException("Exception in SQL", ex.getCause());
        } finally {
            // нет необходимости проверки на нул
            DbaseConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    /**
     * Метод получения выборки из таблиц на основе параметра.
     *
     * @param compareParameter Параметр получения выборки: userId.
     * @return ArrayList занчений из БД.
     * @throws TechException в случае проблем с доступом к БД.
     */
    @Override
    public ArrayList selectAllByParameter(String compareParameter) throws TechException {
        Connection connection = DbaseConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_USERID)) {
            preparedStatement.setString(1, compareParameter);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                returnList.add(
                        new PassHistory(
                                Float.parseFloat(resultSet.getString("passhistory.result")) * 100,
                                Integer.parseInt(resultSet.getString("passhistory.id")),
                                Integer.parseInt(resultSet.getString("passhistory.test_id")),
                                Integer.parseInt(resultSet.getString("passhistory.user_id"))
                        )
                );
            }
            return returnList;

        } catch (SQLException ex) {
            throw new TechException("Exception in SQL in selectAllByParameter", ex.getCause());
        } finally {
            // нет необходимости проверки на нул
            DbaseConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    /**
     * Обновление записи в таблице. Не реализован.
     *
     * @param updateParameter Параметр для обновления.
     * @param args Новые значения вместо старых из БД.
     * @throws TechException в случае проблем с доступом к БД.
     */
    @Override
    public void updateByParameter(String updateParameter, String... args) throws TechException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Удаление по значению параметра. Не реализован.
     *
     * @param deleteParameter Параметр для удаления.
     * @throws TechException в случае проблем с доступом к БД.
     */
    @Override
    public void deleteByParameter(String deleteParameter) throws TechException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Вставка нового элемента в таблицу.
     *
     * @param args Все необходимые аргументы новой записи: testId, userId,
     * result.
     * @throws TechException в случае проблем с доступом к БД.
     */
    @Override
    public void insertNew(String... args) throws TechException {
        Connection connection = DbaseConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ONE)) {
            preparedStatement.setInt(1, Integer.parseInt(args[0]));
            preparedStatement.setInt(2, Integer.parseInt(args[1]));
            preparedStatement.setFloat(3, Float.parseFloat(args[2]));
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new TechException("Exception in SQL in insertNew", ex.getCause());
        } finally {
            // нет необходимости проверки на нул
            DbaseConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    /**
     * Получение ответа, существует ли запись. Не реализован.
     *
     * @param argue Параметр для поиска. Различный для отличных классов.
     * @return true, если запись существует, и false, если нет.
     * @throws TechException в случае проблем с доступом к БД.
     */
    @Override
    public boolean isExist(String argue) throws TechException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Получение идентификатора последней вставленной в БД записи. Не
     * реализован.
     *
     * @return Числовой номер необходимой записи.
     */
    @Override
    public int getLastId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
