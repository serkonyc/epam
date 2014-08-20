/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.testing.daofactory;

import org.epam.testing.daofactory.dao.*;
import org.epam.testing.exception.LogicException;

/**
 *
 * @author Sergiusz
 */
public class DaoFactory {

    public AbstractDao getDaoByName(String daoName) throws LogicException {
        try {
            switch (DaoList.valueOf(daoName.toUpperCase())) {
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
