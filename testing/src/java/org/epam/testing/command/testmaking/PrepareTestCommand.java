/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.testing.command.testmaking;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.epam.testing.command.common.AbstractCommand;
import org.epam.testing.dao.factory.DaoFactory;
import org.epam.testing.dao.AbstractDao;
import org.epam.testing.dao.entity.Subject;
import org.epam.testing.dao.entity.Test;
import org.epam.testing.exception.LogicException;
import org.epam.testing.exception.TechException;
import org.epam.testing.utils.I18nDealer;

/**
 *
 * @author Sergiusz
 */
public class PrepareTestCommand extends AbstractCommand {

    @Override
    public String perform(HttpServletRequest request) throws LogicException, TechException {
        new I18nDealer(this.getClass().getSimpleName()).assignLocale(request);
        AbstractDao dao = new DaoFactory().getDaoByName("test");
        ArrayList<Test> tests = dao.selectAll();
        dao = new DaoFactory().getDaoByName("subject");
        ArrayList<Subject> subjects = dao.selectAll();
        ArrayList<Subject> finalSubjects = new ArrayList<>();

        for (Subject subject : subjects) {
            boolean ishastest = false;
            for (Test test : tests) {
                if (subject.getId() == test.getTheme().getSubj().getId()) {
                    ishastest = true;
                    break;
                }
            }
            if (!ishastest) {
                System.out.println(subject.getId());
                dao.deleteByParameter(String.valueOf(subject.getId()));
            } else {
                finalSubjects.add(subject);
            }
        }        

        request.setAttribute("wascommand", "prepare");
        request.setAttribute("subjs", finalSubjects);
        return flowPagePropertyHandler.getPropertyValue(this.getClass().getSimpleName());
    }

}
