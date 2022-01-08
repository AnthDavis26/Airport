package com.solvd.airport.dao;

import com.solvd.airport.enums.FlightStatus;
import com.solvd.airport.models.Flight;
import com.solvd.airport.models.Schedule;

import java.util.List;

public interface IFlightDAO<T> extends IBaseDAO<T> {
    List<Flight> getFlightsByStatus(FlightStatus flightStatus);
    List<Flight> getFlightsBySchedule(Schedule schedule);
}
