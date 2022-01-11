package com.solvd.airport.dao;

import com.solvd.airport.models.Baggage;

public interface IBaggageDAO<T> extends IBaseDAO<T> {
    Baggage getBaggageByPassengerId(long id);
}
