/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.testing.dao.factory;

import org.epam.testing.dao.AbstractDao;
import org.epam.testing.dao.UserDao;
import org.epam.testing.dao.PassHistoryDao;
import org.epam.testing.dao.SubjectDao;
import org.epam.testing.dao.AnswerDao;
import org.epam.testing.dao.TestDao;
import org.epam.testing.dao.ThemeDao;
import org.epam.testing.dao.QuestDao;
import org.epam.testing.exception.LogicException;

/**
 *
 * @author Sergiusz
 */
public class DaoFactory {

    public AbstractDao getDaoByName(String daoName) throws LogicException {
        try {
            switch (DaoType.valueOf(daoName.toUpperCase())) {
                case USER:
                    return new UserDao();
                case SUBJECT:
                    return new SubjectDao();
                case THEME:
                    return new ThemeDao();
                case TEST:
                    return new TestDao();
                case QUEST:
                    return new QuestDao();
                case ANSWER:
                    return new AnswerDao();
                case PASSHISTORY:
                    return new PassHistoryDao();
                default:
                    throw new LogicException("Trouble with DaoList enum");
            }
        } catch (IllegalArgumentException ex) {
            throw new LogicException("Trouble with DaoList enum", ex.getCause());
        }
    }
}
