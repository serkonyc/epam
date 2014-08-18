/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epam.testing.daofactory.dao;

import java.util.ArrayList;
import org.epam.testing.daofactory.entity.AbstractEntity;
import org.epam.testing.exception.TechException;

/**
 *
 * @author Sergiusz
 */
abstract public class AbstractDao<T extends AbstractEntity> {

    protected ArrayList<T> returnList = new ArrayList<>();

    abstract public ArrayList<T> selectAll()
            throws TechException;

    abstract public ArrayList<T> selectAllByParameter(String compareParameter)
            throws TechException;

    abstract public void updateByParameter(String updateParameter, String... args)
            throws TechException;
    
    abstract public void deleteByParameter(String deleteParameter)
            throws TechException;
    
    abstract public void insertNew(String... args)
            throws TechException;

    abstract public boolean isExist(String argue)
            throws TechException;
    
    abstract public int getLastId();

}
