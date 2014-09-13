/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.utils;

import java.util.ArrayList;

/**
 * Класс отображения списка страниц.
 *
 * @author Sergiusz
 */
public class ByPageReflector {

    /**
     * Метод создания вывода списка страниц.
     *
     * @param source Источник с записями.
     * @param pageNum Номер выбранной страницы.
     * @param totalNum Номер всех страниц.
     */
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
