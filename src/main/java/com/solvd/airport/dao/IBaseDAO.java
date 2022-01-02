package com.solvd.airport.dao;

import java.sql.SQLException;
import java.util.List;

public interface IBaseDAO<T> {
    void createEntity(T entity) throws SQLException;
    void updateEntity(T entity) throws SQLException;
    T getEntityById(long id) throws SQLException;
    void deleteEntityById(long id) throws SQLException;
    List<T> getAllEntities() throws  SQLException;
}
