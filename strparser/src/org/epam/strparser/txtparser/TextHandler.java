/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.strparser.txtparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.epam.strparser.exception.LogicException;
import org.epam.strparser.exception.TechException;
import org.epam.strparser.prophandler.PropertyHandler;

/**
 *
 * @author Sergiusz
 */
public class TextHandler {

    private PropertyHandler matchProp;

    public TextHandler() throws TechException {
        matchProp = new PropertyHandler();
    }

    public String extractText(String fileName) throws TechException {
        BufferedReader bufReader = null;
        try {
            bufReader = new BufferedReader(new FileReader(new File("txtsrc\\" + fileName + ".txt")));
            StringBuilder esxtrResult = new StringBuilder();
            String eachLine = bufReader.readLine();
            while (eachLine != null) {
                esxtrResult.append(eachLine).append("\n");
                eachLine = bufReader.readLine();
            }
            return String.valueOf(esxtrResult);
        } catch (FileNotFoundException ex) {
            throw new TechException("Look for ".concat(fileName).concat(" file in txtsrc."), ex);
        } catch (IOException ex) {
            throw new TechException("Trouble in reading lines!", ex);
        } finally {
            try {
                if (bufReader != null) {
                    bufReader.close();
                }
            } catch (IOException ex) {
                throw new TechException("Trouble with closing bufReader.", ex);
            }
        }
    }

    public ArrayList<String> applyMatchExpression(String regExpr, String fileName) throws TechException, LogicException {
        Pattern p2 = Pattern.compile(regExpr);
        Matcher m2 = p2.matcher(extractText(fileName));
        ArrayList<String> arrayResult = new ArrayList();
        String part;
        StringBuilder codePart = new StringBuilder();
        while (m2.find()) {
            if (m2.group().equals("Код-пример")) {
                codePart.append(m2.group());
            } else if (codePart.toString().contains("Код-пример")) {
                if (!m2.group().equals("кода-примера")) {
                    codePart.append(m2.group());
                } else {
                    arrayResult.add(codePart.append(m2.group()).toString());
                    codePart.delete(0, codePart.length());
                }
            } else {
                part = m2.group();
                if (!part.equals("")) {
                    arrayResult.add(part);
                } else {
                    throw new LogicException("Nullsized lexeme part at applySplitExpression!");
                }
            }
        }
        return arrayResult;
    }
}
