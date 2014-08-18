/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.testing.daofactory.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.epam.testing.daofactory.entity.Subject;
import org.epam.testing.dbconnection.DbaseConnectionPool;
import org.epam.testing.exception.TechException;

/**
 *
 * @author Sergiusz
 */
public class SubjectDao extends AbstractDao<Subject> {

    private final String SELECT_ALL = "SELECT * FROM subject";
    private final String MATCH_ONE = "SELECT * FROM subject WHERE name like (?)";
    private final String INSERT_ONE = "INSERT INTO subject(name) VALUES (?)";

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

    @Override
    public ArrayList<Subject> selectAllByParameter(String compareParameter) throws TechException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getLastId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteByParameter(String deleteParameter) throws TechException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateByParameter(String updateParameter, String... args) throws TechException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
