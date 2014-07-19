/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.strparser.control;

import org.epam.strparser.exception.LogicException;
import org.epam.strparser.exception.TechException;
import org.epam.strparser.prophandler.PropertyHandler;
import org.epam.strparser.txtparser.TextHandler;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 *
 * @author Sergiusz
 */
public class MainMaker {

    static final Logger LOGGER = Logger.getLogger("console-file");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DOMConfigurator.configure("log4j.xml");
        try {
            PropertyHandler propeHanl = new PropertyHandler();
            TextHandler txtHanl = new TextHandler();
            LexemeCompiler result = new LexemeCompiler(txtHanl.applyMatchExpression(propeHanl.getPropertyValue("lexeme"), "regular"));
            System.out.println(result.compileResult().getContent());
        } catch (TechException | LogicException ex) {
            LOGGER.error(ex);
        }
    }

}
