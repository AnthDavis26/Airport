package com.solvd.airport.services;

import java.util.List;

public interface IBaseService<T> {
    void create(T entity);
    void delete(T entity);
    List<T> getAll();
}
