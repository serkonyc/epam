/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.testing.prophandler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.epam.testing.exception.TechException;

/**
 *
 * @author Sergiusz
 */
public class PropertyHandler {

    private static String path;
    private static InputStream input;

    private Properties prop;

    public PropertyHandler(String propertyFileName) throws TechException {
        try {
            input = new FileInputStream(path = path.concat(propertyFileName));
            prop = new Properties();
            prop.load(input);

        } catch (FileNotFoundException ex) {
            throw new TechException("Look for properties file regexs!", ex);
        } catch (IOException ex) {
            throw new TechException("Some trouble with loading properties.", ex);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    throw new TechException("Trouble with closing stream.", ex);
                }
            }
        }
    }

    public String getPropertyValue(String propName) throws TechException {
        if (prop.getProperty(propName) != null) {
            return prop.getProperty(propName);
        } else {
            throw new TechException("Nonexistent property!");
        }
    }

    public static void setInput(String servPath) {
        path = servPath.substring(0, servPath.length() - 10).concat("\\src\\properties\\");
    }
}
