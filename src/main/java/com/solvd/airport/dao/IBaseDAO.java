package com.solvd.airport.dao;

import java.util.List;

public interface IBaseDAO<T> {
    void createEntity(T entity);
    void updateEntity(T entity);
    T getEntityById(long id);
    void deleteEntityById(long id);
    List<T> getAllEntities() ;
}
