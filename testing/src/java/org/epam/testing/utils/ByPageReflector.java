/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.testing.utils;

import java.util.ArrayList;

/**
 *
 * @author Sergiusz
 */
public class ByPageReflector {

    public static void makeOutput(ArrayList source, int pageNum, int totalNum) {
        if (pageNum > 6) {
            source.add(1);
            source.add("...");
            for (int i = pageNum - 4; i < pageNum + 5 && i < totalNum; i++) {
                source.add(i);
            }
        } else {
            for (int i = 1; i < totalNum + 1 && i < 10; i++) {
                source.add(i);
            }

        }
        source.add("...");
        source.add(totalNum);
    }

}
