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
import org.epam.testing.dao.entity.Theme;
import org.epam.testing.dbconnection.DbaseConnectionPool;
import org.epam.testing.exception.TechException;

/**
 * Класс работы с сущностями темы.
 *
 * @author Sergiusz
 */
public class ThemeDao extends AbstractDao<Theme> {

    private final String SELECT_ALL = "SELECT * FROM theme, subject WHERE theme.subject_id=subject.id";
    private final String SELECT_ALL_BY_SUBJECTNAME = "SELECT * FROM theme, subject WHERE subject.name = (?) AND theme.subject_id=subject.id";
    private final String MATCH_ONE = "SELECT * FROM theme WHERE name like (?)";
    private final String INSERT_ONE = "INSERT INTO theme(subject_id,name) VALUES (?,?)";
    private final String DELETE_BY = "DELETE FROM theme WHERE theme.id = (?)";

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
                        new Theme(
                                new Subject(
                                        Integer.parseInt(resultSet.getString("subject.id")),
                                        resultSet.getString("subject.name")
                                ),
                                Integer.parseInt(resultSet.getString("theme.id")),
                                resultSet.getString("theme.name")
                        )
                );
            }
            return returnList;

        } catch (SQLException ex) {
            throw new TechException("Exception in SQL in selectAll", ex.getCause());
        } finally {
            // нет необходимости проверки на нул
            DbaseConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    /**
     * Вставка нового элемента в таблицу.
     *
     * @param args Все необходимые аргументы новой записи: subjectId, name.
     * @throws TechException в случае проблем с доступом к БД.
     */
    @Override
    public void insertNew(String... args) throws TechException {
        Connection connection = DbaseConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ONE)) {
            preparedStatement.setInt(1, Integer.parseInt(args[0]));
            preparedStatement.setString(2, args[1]);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new TechException("Exception in SQL in insertNew", ex.getCause());
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
            throw new TechException("Exception in SQL in isExist", ex.getCause());
        } finally {
            // нет необходимости проверки на нул
            DbaseConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    /**
     * Метод получения выборки из таблиц на основе параметра.
     *
     * @param compareParameter Параметр получения выборки: subjectName.
     * @return ArrayList занчений из БД.
     * @throws TechException в случае проблем с доступом к БД.
     */
    @Override
    public ArrayList<Theme> selectAllByParameter(String compareParameter) throws TechException {
        Connection connection = DbaseConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_SUBJECTNAME)) {
            preparedStatement.setString(1, compareParameter);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                returnList.add(
                        new Theme(
                                new Subject(
                                        Integer.parseInt(resultSet.getString("subject.id")),
                                        resultSet.getString("subject.name")
                                ),
                                Integer.parseInt(resultSet.getString("theme.id")),
                                resultSet.getString("theme.name")
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
