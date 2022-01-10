package com.solvd.airport.dao;

import com.solvd.airport.models.Baggage;
import com.solvd.airport.models.User;

import java.sql.SQLException;

public interface IBaggageDAO<T> extends IBaseDAO<T> {
    Baggage getBaggageByPassengerId(long id);
}
