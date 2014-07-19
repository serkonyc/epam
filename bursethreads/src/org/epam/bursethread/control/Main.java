/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.bursethread.control;

import java.util.concurrent.Semaphore;
import org.apache.log4j.xml.DOMConfigurator;
import org.epam.bursethread.burse.Burse;

/**
 *
 * @author Sergiusz
 */
public class Main {

    private static Burse b;

    public static void callSounder() {
        String infoMess = b.getSounder().getHistory();
        if (infoMess != null && !infoMess.isEmpty()) {
            System.out.print(b.getSounder().getHistory());
            b.getSounder().clearHistory();
        }
    }

    public static void main(String[] args) {
        DOMConfigurator.configure("log4j.xml");

        b = new Burse(new Semaphore(1));

        b.startAddingThread();
        b.startTradingThread();
        b.startAddingThread();
    }
}