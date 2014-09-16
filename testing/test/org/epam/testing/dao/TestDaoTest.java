/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.dao;

import java.util.ArrayList;
import org.epam.testing.prophandler.PropertyHandler;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sergiusz
 */
public class TestDaoTest {

    public TestDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of selectAll method, of class TestDao.
     */
    @Test
    public void testSelectAll() throws Exception {
        PropertyHandler.setInput("C:/Users/Sergiusz/Documents/NetBeansProjects/testing/build/web");
        TestDao instance = new TestDao();
        instance.insertNew("33", "30", "5");
        int startnum = instance.selectAll().size();
        instance.deleteByParameter(String.valueOf(instance.getLastId()));
        int finnum = instance.selectAll().size();
        System.out.println(startnum);
        System.out.println(finnum);
        assertTrue(finnum + 1 == startnum * 2);
    }

    /**
     * Test of insertNew method, of class TestDao.
     */
    @Test
    public void testInsertNew() throws Exception {
        PropertyHandler.setInput("C:/Users/Sergiusz/Documents/NetBeansProjects/testing/build/web");
        TestDao instance = new TestDao();
        instance.insertNew("33", "30", "5");
        boolean asserts = false;
        int testId = instance.getLastId();
        ArrayList<org.epam.testing.dao.entity.Test> testList = instance.selectAll();
        for (org.epam.testing.dao.entity.Test test : testList) {
            if (test.getId() == testId) {
                if (test.getTutorId() == 33) {
                    asserts = true;
                    instance.deleteByParameter(String.valueOf(testId));
                }
            }
        }
        assertTrue(asserts);
    }
}
