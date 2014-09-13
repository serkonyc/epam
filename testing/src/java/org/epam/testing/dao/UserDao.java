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
import org.epam.testing.dao.entity.User;
import org.epam.testing.dbconnection.DbaseConnectionPool;
import org.epam.testing.exception.TechException;

/**
 * Класс работы с сущностями пользователя.
 *
 * @author Sergiusz
 */
public class UserDao extends AbstractDao<User> {

    private int lastInsertedId;

    private final String SELECT_ALL = "SELECT * FROM user";
    private final String INSERT_ONE = "INSERT INTO user(nick, email, role, pass) VALUES (?, ?, ?, ?)";
    private final String DELETE_BY = "DELETE FROM user WHERE user.id = (?)";
    private final String UPDATE_ONE = "UPDATE user SET user.nick = (?), user.email = (?), user.role = (?) WHERE user.id = (?)";

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
                        new User(
                                Integer.parseInt(resultSet.getString("user.id")),
                                resultSet.getString("user.nick"),
                                resultSet.getString("user.email"),
                                resultSet.getString("user.role"),
                                resultSet.getString("user.pass")
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
     * @param args Все необходимые аргументы новой записи: nick, email, role,
     * pass.
     * @throws TechException в случае проблем с доступом к БД.
     */
    @Override
    public void insertNew(String... args) throws TechException {
        Connection connection = DbaseConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ONE)) {
            for (int i = 1; i <= 4; i++) {
                if (args[i - 1] == null) {
                    args[i - 1] = "";
                }
                preparedStatement.setString(i, args[i - 1]);
            }
            preparedStatement.executeUpdate();
            ResultSet some = preparedStatement.executeQuery("SELECT LAST_INSERT_ID()");
            if (some.next()) {
                lastInsertedId = some.getInt(1);
            }

        } catch (SQLException ex) {
            throw new TechException("Exception in SQL", ex.getCause());
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
     * Метод получения выборки из таблиц на основе параметра.
     *
     * @param compareParameter Параметр получения выборки, определяемый для
     * каждого класса в отдельности.
     * @return ArrayList занчений из БД.
     * @throws TechException в случае проблем с доступом к БД.
     */
    @Override
    public ArrayList selectAllByParameter(String compareParameter) throws TechException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Получение идентификатора последней вставленной в БД записи.
     *
     * @return Числовой номер необходимой записи.
     */
    @Override
    public int getLastId() {
        return lastInsertedId;
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
            throw new TechException("Exception in SQL in insertNew", ex.getCause());
        } finally {
            // нет необходимости проверки на нул
            DbaseConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    /**
     * Обновление записи в таблице.
     *
     * @param updateParameter Параметр для обновления: id.
     * @param args Новые значения вместо старых из БД: nick, email, role.
     * @throws TechException в случае проблем с доступом к БД.
     */
    @Override
    public void updateByParameter(String updateParameter, String... args) throws TechException {
        Connection connection = DbaseConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ONE)) {
            preparedStatement.setInt(4, Integer.parseInt(updateParameter));
            preparedStatement.setString(1, args[0]);
            preparedStatement.setString(2, args[1]);
            preparedStatement.setString(3, args[2]);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new TechException("Exception in SQL in insertNew", ex.getCause());
        } finally {
            // нет необходимости проверки на нул
            DbaseConnectionPool.getInstance().releaseConnection(connection);
        }
    }

}
