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
import org.epam.testing.daofactory.entity.Theme;
import org.epam.testing.dbconnection.DbaseConnectionPool;
import org.epam.testing.exception.TechException;

/**
 *
 * @author Sergiusz
 */
public class ThemeDao extends AbstractDao<Theme> {

    private final String SELECT_ALL = "SELECT * FROM theme, subject WHERE theme.subject_id=subject.id";
    private final String SELECT_ALL_BY_SUBJECTNAME = "SELECT * FROM theme, subject WHERE subject.name = (?) AND theme.subject_id=subject.id";
    private final String MATCH_ONE = "SELECT * FROM theme WHERE name like (?)";
    private final String INSERT_ONE = "INSERT INTO theme(subject_id,name) VALUES (?,?)";

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
                                Integer.parseInt(resultSet.getString("theme.subject_id")),
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
                                Integer.parseInt(resultSet.getString("theme.subject_id")),
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
