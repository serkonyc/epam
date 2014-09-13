/*
 * Copyright (C) 2014 Sergiusz
 *
 */
package org.epam.testing.dao;

import java.util.ArrayList;
import org.epam.testing.dao.entity.AbstractEntity;
import org.epam.testing.exception.TechException;

/**
 * Абстрактный DAO-класс.
 * 
 * @author Sergiusz
 * @param <T> Параметризация определёнными сущностями.
 */
abstract public class AbstractDao<T extends AbstractEntity> {

    /**
     *  Список возвращаемых параметризованных значений.
     */
    protected ArrayList<T> returnList = new ArrayList<>();

    /**
     * Метод получения полной выборки 
     * из базы данных.
     * 
     * @return ArrayList всех элементов.
     * @throws TechException в случае проблем с доступом к БД.
     */
    abstract public ArrayList<T> selectAll()
            throws TechException;

    /**
     * Метод получения выборки из таблиц
     * на основе параметра.
     * 
     * @param compareParameter Параметр получения выборки, определяемый для каждого класса в отдельности.
     * @return ArrayList занчений из БД.
     * @throws TechException в случае проблем с доступом к БД.
     */
    abstract public ArrayList<T> selectAllByParameter(String compareParameter)
            throws TechException;

    /**
     * Обновление записи в таблице.
     * 
     * @param updateParameter Параметр для обновления.
     * @param args Новые значения вместо старых из БД.
     * @throws TechException в случае проблем с доступом к БД.
     */
    abstract public void updateByParameter(String updateParameter, String... args)
            throws TechException;
    
    /**
     * Удаление по значению параметра.
     * 
     * @param deleteParameter Параметр для удаления.
     * @throws TechException в случае проблем с доступом к БД.
     */
    abstract public void deleteByParameter(String deleteParameter)
            throws TechException;
    
    /**
     * Вставка нового элемента в таблицу.
     * 
     * @param args Все необходимые аргументы новой записи.
     * @throws TechException в случае проблем с доступом к БД.
     */
    abstract public void insertNew(String... args)
            throws TechException;

    /**
     * Получение ответа, существует ли запись.
     * 
     * @param argue Параметр для поиска. Различный для отличных классов.
     * @return true, если запись существует, и false, если нет.
     * @throws TechException в случае проблем с доступом к БД.
     */
    abstract public boolean isExist(String argue)
            throws TechException;
    
    /**
     * Получение последнего идентификатора 
     * последней вставленной в БД записи.
     * 
     * @return Числовой номер необходимой записи.
     */
    abstract public int getLastId();

}
