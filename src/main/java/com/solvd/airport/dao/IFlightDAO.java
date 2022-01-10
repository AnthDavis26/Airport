package com.solvd.airport.dao;

import com.solvd.airport.enums.FlightStatus;
import com.solvd.airport.models.Flight;

import java.sql.SQLException;
import java.util.List;

public interface IFlightDAO<T> extends IBaseDAO<T> {
    List<Flight> getFlightsByStatus(FlightStatus flightStatus);
    List<Flight> getFlightsByScheduleId(long id);
}
