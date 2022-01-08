package com.solvd.airport.dao;

import com.solvd.airport.models.Airport;

import java.sql.SQLException;

public interface IAirportDAO<T> extends IBaseDAO<T> {
    Airport getAirportByName(String name) throws SQLException;
    Airport getAirportByIATA(String iata) throws SQLException;
}
