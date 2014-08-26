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
import org.epam.testing.dao.entity.Answer;
import org.epam.testing.dbconnection.DbaseConnectionPool;
import org.epam.testing.exception.TechException;

/**
 *
 * @author Sergiusz
 */
public class AnswerDao extends AbstractDao {

    private final String INSERT_ONE = "INSERT INTO answer(quest_id, text, isright) VALUES (?,?,?)";
    private final String SELECT_ALL_BY_TESTID = "SELECT * FROM answer, quest WHERE quest.test_id = (?) AND answer.quest_id = quest.id";
    private final String UPDATE_ONE = "UPDATE answer SET answer.text = (?) WHERE answer.id = (?)";

    @Override
    public ArrayList selectAll() throws TechException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList selectAllByParameter(String compareParameter) throws TechException {
        Connection connection = DbaseConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_TESTID)) {
            preparedStatement.setInt(1, Integer.parseInt(compareParameter));
            ResultSet resultSet = preparedStatement.executeQuery();
            {
                boolean isRight;
                while (resultSet.next()) {
                    isRight = !resultSet.getString("answer.isright").equals("0");
                    if (isRight) {
                        //System.out.println(resultSet.getString("quest.test_id"));
                        returnList.add(
                                new Answer(
                                        Integer.parseInt(resultSet.getString("answer.id")),
                                        Integer.parseInt(resultSet.getString("answer.quest_id")),
                                        isRight,
                                        resultSet.getString("answer.text")
                                )
                        );
                    }
                }
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
    public void insertNew(String... args) throws TechException {
        Connection connection = DbaseConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ONE)) {
            preparedStatement.setInt(1, Integer.parseInt(args[0]));
            preparedStatement.setString(2, args[1]);
            preparedStatement.setBoolean(3, Boolean.valueOf(args[2]));
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
        Connection connection = DbaseConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ONE)) {
            preparedStatement.setInt(2, Integer.parseInt(updateParameter));
            preparedStatement.setString(1, args[0]);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new TechException("Exception in SQL in insertNew", ex.getCause());
        } finally {
            // нет необходимости проверки на нул
            DbaseConnectionPool.getInstance().releaseConnection(connection);
        }
    }

}
