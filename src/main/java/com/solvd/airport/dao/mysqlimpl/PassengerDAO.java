package com.solvd.airport.dao.mysqlimpl;

import com.solvd.airport.dao.IPassengerDAO;
import com.solvd.airport.models.Passenger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.util.List;

public class PassengerDAO extends AbstractMySQLDAO<Passenger> implements IPassengerDAO<Passenger> {
    private static final Logger logger = LogManager.getLogger(PassengerDAO.class);
    private static final String GET_PASSENGER_BY_ID = "SELECT * FROM passengers WHERE id=?";
    private static final String GET_PASSENGER_BY_USER_ID = "SELECT * FROM passengers WHERE user_id=?";
    private static final String GET_PASSENGER_BY_FLIGHT_ID = "SELECT * FROM passengers WHERE flight_id=?";
    private static final String INSERT_PASSENGER = "INSERT INTO passengers (user_id, flight_id) VALUES(?,?)";
    private static final String UPDATE_PASSENGER_BY_ID = "UPDATE passengers SET user_id=?, flight_id=? " +
            "WHERE id=?";
    private static final String DELETE_PASSENGER_BY_ID = "DELETE FROM passengers WHERE id=?";
    private static final String GET_ALL_PASSENGERS = "SELECT * FROM passengers";

    @Override
    public void createEntity(Passenger passenger) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(INSERT_PASSENGER);
                st.setLong(1, passenger.getUserId());
                st.setLong(2, passenger.getFlightId());
            } catch (Exception e) {
                logger.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public void updateEntity(Passenger passenger) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(UPDATE_PASSENGER_BY_ID);
                st.setLong(1, passenger.getUserId());
                st.setLong(2, passenger.getFlightId());
                st.setLong(3, passenger.getId());
            } catch (Exception e) {
                logger.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public Passenger getEntityById(long id) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_PASSENGER_BY_ID);
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
                st = con.prepareStatement(DELETE_PASSENGER_BY_ID);
                st.setLong(1, id);
            } catch (Exception e) {
                logger.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public List<Passenger> getAllEntities() {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_ALL_PASSENGERS);
            } catch (Exception e) {
                logger.error(e);
            }
        };

        return getEntitiesHelper(runnable);
    }

    @Override
    protected Passenger resultSetToEntity(ResultSet rs) {
        Passenger passenger = new Passenger();

        try {
            passenger.setId(rs.getLong("id"));
            passenger.setFlightId(rs.getLong("flight_id"));
            passenger.setUserId(rs.getLong("user_id"));
        } catch (Exception e) {
            logger.error(e);
        }

        return passenger;
    }
}
