package com.solvd.airport.dao;

import com.solvd.airport.models.Airport;

public interface IAirportDAO<T> extends IBaseDAO<T> {
    Airport getAirportByName(long id);
    Airport getAirportByIATA(String iata);
}
