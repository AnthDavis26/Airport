package com.solvd.airport.dao;

import com.solvd.airport.models.Airline;

import java.sql.SQLException;
import java.util.List;

public interface IAirlineDAO<T> extends IBaseDAO<T> {
    List<Airline> getAirlinesByName(String name) throws SQLException;
    List<Airline> getAirlinesByTerminalId(long id) throws SQLException;
}
