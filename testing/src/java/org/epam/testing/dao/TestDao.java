/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.testing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.epam.testing.dao.entity.Subject;
import org.epam.testing.dao.entity.Test;
import org.epam.testing.dao.entity.Theme;
import org.epam.testing.dbconnection.DbaseConnectionPool;
import org.epam.testing.exception.TechException;

/**
 *
 * @author Sergiusz
 */
public class TestDao extends AbstractDao<Test> {

    private int lastInsertedId;

    private final String SELECT_ALL = "SELECT * FROM test, theme, subject WHERE test.theme_id=theme.id AND theme.subject_id=subject.id";
    private final String INSERT_ONE = "INSERT INTO test(quest_num, tutor_id, theme_id) VALUES (?,?,?)";
    private final String DELETE_BY = "DELETE FROM test WHERE test.id = (?)";

    @Override
    public ArrayList selectAll() throws TechException {
        Connection connection = DbaseConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                returnList.add(
                        new Test(
                                new Theme(
                                        new Subject(
                                                Integer.parseInt(resultSet.getString("subject.id")),
                                                resultSet.getString("subject.name")
                                        ),
                                        Integer.parseInt(resultSet.getString("theme.id")),
                                        Integer.parseInt(resultSet.getString("theme.subject_id")),
                                        resultSet.getString("theme.name")
                                ),
                                Integer.parseInt(resultSet.getString("test.id")),
                                Integer.parseInt(resultSet.getString("test.theme_id")),
                                Integer.parseInt(resultSet.getString("test.tutor_id"))
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

    @Override
    public void insertNew(String... args) throws TechException {
        Connection connection = DbaseConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ONE)) {
            preparedStatement.setInt(1, Integer.parseInt(args[2]));
            preparedStatement.setString(2, args[0]);
            preparedStatement.setString(3, args[1]);
            preparedStatement.executeUpdate();
            ResultSet some = preparedStatement.executeQuery("SELECT LAST_INSERT_ID()");
            if (some.next()) {
                lastInsertedId = some.getInt(1);
            }
        } catch (SQLException ex) {
            throw new TechException("Exception in SQL in insertNew", ex.getCause());
        } finally {
            // нет необходимости проверки на нул
            DbaseConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public boolean isExist(String argue) throws TechException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Test> selectAllByParameter(String compareParameter) throws TechException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getLastId() {
        return lastInsertedId;
    }

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

    @Override
    public void updateByParameter(String updateParameter, String... args) throws TechException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
