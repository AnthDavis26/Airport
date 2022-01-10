package com.solvd.airport.dao.mysqlimpl;

import com.solvd.airport.dao.IFlightDAO;
import com.solvd.airport.enums.FlightStatus;
import com.solvd.airport.models.Flight;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FlightDAO extends AbstractMySQLDAO<Flight> implements IFlightDAO<Flight> {
    private static final Logger logger = LogManager.getLogger(FlightDAO.class);
    private static final String INSERT_FLIGHT = "INSERT INTO flights (schedule_id, status, " +
            "gate_id, vehicle_id) VALUES(?,?,?,?)";
    private static final String UPDATE_FLIGHT_BY_ID = "UPDATE flights schedule_id=?, status=?, " +
            "gate_id=?, vehicle_id=? WHERE id=?";
    private static final String GET_FLIGHT_BY_ID = "SELECT * FROM flights WHERE id = ?";
    private static final String DELETE_FLIGHT_BY_ID = "DELETE FROM flights WHERE id = ?";
    private static final String GET_ALL_FLIGHTS = "SELECT * FROM flights";
    private static final String GET_FLIGHTS_BY_STATUS = "SELECT * FROM flights WHERE status=?";
    private static final String GET_FLIGHTS_BY_SCHEDULE_ID = "SELECT * FROM flights WHERE schedule_id=?";

    @Override
    public void createEntity(Flight flight) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(INSERT_FLIGHT);
                st.setLong(1, flight.getScheduleId());
                st.setString(2, flight.getStatus().toString());
                st.setLong(3, flight.getGateId());
                st.setLong(4, flight.getVehicleId());
            } catch (Exception e) {
                logger.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public void updateEntity(Flight flight) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(UPDATE_FLIGHT_BY_ID);
                st.setLong(1, flight.getScheduleId());
                st.setString(2, flight.getStatus().toString());
                st.setLong(3, flight.getGateId());
                st.setLong(4, flight.getVehicleId());
                st.setLong(5, flight.getId());
            } catch (Exception e) {
                logger.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public Flight getEntityById(long id) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_FLIGHT_BY_ID);
                st.setLong(1, id);
            } catch (Exception e) {
                logger.error(e);
            }
        };

        return getEntitiesHelper(runnable).get(0);
    }

    @Override
    public void deleteEntityById(long id) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(DELETE_FLIGHT_BY_ID);
                st.setLong(1, id);
            } catch (Exception e) {
                logger.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public List<Flight> getAllEntities() {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_ALL_FLIGHTS);
            } catch (Exception e) {
                logger.error(e);
            }
        };

        return getEntitiesHelper(runnable);
    }

    @Override
    public List<Flight> getFlightsByStatus(FlightStatus flightStatus) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_FLIGHTS_BY_STATUS);
                st.setString(1, flightStatus.getMessage());
            } catch (Exception e) {
                logger.error(e);
            }
        };

        return getEntitiesHelper(runnable);
    }

    @Override
    public List<Flight> getFlightsByScheduleId(long id) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_FLIGHTS_BY_SCHEDULE_ID);
                st.setLong(1, id);
            } catch (Exception e) {
                logger.error(e);
            }
        };

        return getEntitiesHelper(runnable);
    }

    @Override
    protected Flight resultSetToEntity(ResultSet rs) {
        Flight flight = new Flight();

        try {
            flight.setId(rs.getLong("id"));
            flight.setScheduleId(rs.getLong("schedule_id"));

            String flightStatus = rs.getString("status");

            if (flightStatus == FlightStatus.ON_TIME.getMessage())
                flight.setStatus(FlightStatus.ON_TIME);
            else if (flightStatus == FlightStatus.DELAYED.getMessage())
                flight.setStatus(FlightStatus.DELAYED);

            flight.setGateId(rs.getLong("gate_id"));
            flight.setVehicleId(rs.getLong("vehicle_id"));
        } catch (Exception e) {
            logger.error(e);
        }

        return flight;
    }
}
