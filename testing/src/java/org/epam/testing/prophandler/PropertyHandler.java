/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.prophandler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import org.epam.testing.exception.TechException;

/**
 * Класс работы с данными из properties.
 *
 * @author Sergiusz
 */
public class PropertyHandler {

    private static String path;
    private static InputStream input;

    private Properties prop;

    /**
     * Конструктор обработчика свойств.
     *
     * @param propertyFileName Имя файла к открытию.
     * @throws TechException в случае технических проблем.
     */
    public PropertyHandler(String propertyFileName) throws TechException {
        try {
            String localpath = path.concat(propertyFileName);
            input = new FileInputStream(localpath);
            prop = new Properties();
            prop.load(input);
        } catch (FileNotFoundException ex) {
            throw new TechException("Look for properties file!", ex);
        } catch (IOException ex) {
            throw new TechException("Some trouble with loading properties.", ex);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    input = null;
                }
            }
        }
    }

    /**
     * Метод получения определённого свойства из файлов.
     *
     * @param propName Название искомого свойства.
     * @return Значение свойства.
     * @throws TechException в случае технических ошибок.
     */
    public String getPropertyValue(String propName) throws TechException {
        if (prop.getProperty(propName) != null) {
            return prop.getProperty(propName);
        } else {
            throw new TechException("Nonexistent property propName!");
        }
    }

    /**
     * Метод получения отсортированного множества ключей из файла.
     *
     * @return Множество неповторяющихся ключей.
     */
    public Set getKeysSet() {
        TreeSet keys = new TreeSet();
        keys.addAll(prop.keySet());
        return keys;
    }

    /**
     * Метод установки пути к файлам.
     *
     * @param servPath Путь к папке проекта, переданный с сервлета.
     */
    public static void setInput(String servPath) {
        path = servPath.substring(0, servPath.length() - 10).concat("\\src\\properties\\");
    }
}
