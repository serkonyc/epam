/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.utils;

import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sergiusz
 */
public class ByPageReflectorTest {

    public ByPageReflectorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of makeOutput method, of class ByPageReflector.
     */
    @Test
    public void testMakeOutput() {
        ArrayList source = new ArrayList();
        int pageNum = 1;
        int totalNum = 1;
        ByPageReflector.makeOutput(source, pageNum, totalNum);
        ArrayList test = new ArrayList();
        {
            test.add(1);
            test.add("...");
            test.add(1);
        }
        assertEquals(source, test);
        pageNum = 3;
        totalNum = 6;
        ByPageReflector.makeOutput(source, pageNum, totalNum);
        {
            test.add(1);
            test.add(2);
            test.add(3);
            test.add(4);
            test.add(5);
            test.add(6);
            test.add("...");
            test.add(6);
        }
        assertEquals(source, test);
        pageNum = 8;
        totalNum = 20;
        ByPageReflector.makeOutput(source, pageNum, totalNum);
        {
            test.add(1);
            test.add("...");
            test.add(4);
            test.add(5);
            test.add(6);
            test.add(7);
            test.add(8);
            test.add(9);
            test.add(10);
            test.add(11);
            test.add(12);
            test.add("...");
            test.add(20);
        }
        assertEquals(source, test);
    }

}
