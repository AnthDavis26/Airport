package com.solvd.airport.dao;

import java.sql.SQLException;

public interface IBaseDAO<T> {
    void createEntity(T entity);
    void updateEntity(T entity);
    T getEntityById(long id) throws SQLException;
    void deleteEntityById(long id) throws SQLException;
}
