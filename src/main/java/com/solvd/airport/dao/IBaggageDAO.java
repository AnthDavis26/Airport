package com.solvd.airport.dao;

import com.solvd.airport.models.User;

public interface IBaggageDAO<T> extends IBaseDAO<T> {
    User getUserByPassengerId(long id);
}
