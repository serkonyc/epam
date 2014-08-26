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
import org.epam.testing.dao.entity.Quest;
import org.epam.testing.dbconnection.DbaseConnectionPool;
import org.epam.testing.exception.TechException;

/**
 *
 * @author Sergiusz
 */
public class QuestDao extends AbstractDao {

    private int lastInsertedId;

    private final String INSERT_ONE = "INSERT INTO quest(test_id, text) VALUES (?,?)";
    private final String SELECT_ALL_BY_TESTID = "SELECT * FROM quest, answer WHERE quest.test_id = (?) AND quest.id=answer.quest_id";
    private final String UPDATE_ONE = "UPDATE quest SET quest.text = (?) WHERE quest.id = (?)";
            
    @Override
    public ArrayList selectAll() throws TechException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList selectAllByParameter(String compareParameter) throws TechException {
        Connection connection = DbaseConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_TESTID)) {
            preparedStatement.setString(1, compareParameter);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = 0;
            Answer[] arrAns = new Answer[5];
            while (resultSet.next()) {
                arrAns[count] = new Answer(
                        Integer.parseInt(resultSet.getString("answer.id")),
                        Integer.parseInt(resultSet.getString("quest.id")),
                        Boolean.getBoolean(resultSet.getString("answer.isright")),
                        resultSet.getString("answer.text")
                );
                if (count != 0 && count % 4 == 0) {
                    returnList.add(
                            new Quest(
                                    Integer.parseInt(resultSet.getString("quest.id")),
                                    Integer.parseInt(resultSet.getString("quest.test_id")),
                                    resultSet.getString("quest.text"),
                                    arrAns
                            )
                    );
                    count = 0;
                } else {
                    count++;
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
    public int getLastId() {
        return lastInsertedId;
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
