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
import org.epam.testing.dao.entity.Subject;
import org.epam.testing.dbconnection.DbaseConnectionPool;
import org.epam.testing.exception.TechException;

/**
 * Класс работы с сущностями предмета.
 *
 * @author Sergiusz
 */
public class SubjectDao extends AbstractDao<Subject> {

    private final String SELECT_ALL = "SELECT * FROM subject";
    private final String MATCH_ONE = "SELECT * FROM subject WHERE name like (?)";
    private final String INSERT_ONE = "INSERT INTO subject(name) VALUES (?)";
    private final String DELETE_BY = "DELETE FROM subject WHERE subject.id = (?)";

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
                        new Subject(
                                Integer.parseInt(resultSet.getString("subject.id")),
                                resultSet.getString("subject.name")
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
     * Вставка нового элемента в таблицу.
     *
     * @param args Все необходимые аргументы новой записи: name.
     * @throws TechException в случае проблем с доступом к БД.
     */
    @Override
    public void insertNew(String... args) throws TechException {
        Connection connection = DbaseConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ONE)) {
            preparedStatement.setString(1, args[0]);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new TechException("Exception in SQL", ex.getCause());
        } finally {
            // нет необходимости проверки на нул
            DbaseConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    /**
     * Получение ответа, существует ли запись.
     *
     * @param argue Параметр для поиска: name.
     * @return true, если запись существует, и false, если нет.
     * @throws TechException в случае проблем с доступом к БД.
     */
    @Override
    public boolean isExist(String argue) throws TechException {
        Connection connection = DbaseConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(MATCH_ONE)) {
            preparedStatement.setString(1, argue);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException ex) {
            throw new TechException("Exception in SQL", ex.getCause());
        } finally {
            // нет необходимости проверки на нул
            DbaseConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    /**
     * Метод получения выборки из таблиц на основе параметра. Не реализован.
     *
     * @param compareParameter Параметр получения выборки, определяемый для
     * каждого класса в отдельности.
     * @return ArrayList занчений из БД.
     * @throws TechException в случае проблем с доступом к БД.
     */
    @Override
    public ArrayList<Subject> selectAllByParameter(String compareParameter) throws TechException {
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

    /**
     * Удаление по значению параметра.
     *
     * @param deleteParameter Параметр для удаления: id.
     * @throws TechException в случае проблем с доступом к БД.
     */
    @Override
    public void deleteByParameter(String deleteParameter) throws TechException {
        Connection connection = DbaseConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY)) {
            preparedStatement.setString(1, deleteParameter);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new TechException("Exception in SQL in deleteByParameter", ex.getCause());
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

}
