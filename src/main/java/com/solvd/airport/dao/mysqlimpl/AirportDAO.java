package com.solvd.airport.dao.mysqlimpl;

import com.solvd.airport.dao.IAirportDAO;
import com.solvd.airport.models.Airport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AirportDAO extends AbstractMySQLDAO<Airport> implements IAirportDAO<Airport> {
    private static final Logger logger = LogManager.getLogger(AirportDAO.class);
    private static final String GET_AIRPORT_BY_ID = "SELECT * FROM airports WHERE id = ?";
    private static final String GET_AIRPORTS_BY_NAME = "SELECT * FROM airports WHERE name = ?";
    private static final String GET_AIRPORTS_BY_IATA = "SELECT * FROM airports WHERE iata = ?";
    private static final String INSERT_AIRPORT = "INSERT INTO airports (name, iata) VALUES(?,?)";
    private static final String UPDATE_AIRPORT_BY_ID = "UPDATE airports SET name = ?, iata = ? " +
            "WHERE id = ?";
    private static final String DELETE_AIRPORT_BY_ID = "DELETE FROM airports WHERE id = ?";
    private static final String GET_ALL_AIRPORTS = "SELECT * FROM airports";

    @Override
    public Airport getAirportByName(String name) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_AIRPORTS_BY_NAME);
                st.setString(1, name);
            } catch (Exception e) {
                logger.error(e);
            }
        };

        return getEntitiesHelper(runnable).get(0);
    }

    @Override
    public Airport getAirportByIATA(String iata) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_AIRPORTS_BY_IATA);
                st.setString(1, iata);
            } catch (Exception e) {
                logger.error(e);
            }
        };

        return getEntitiesHelper(runnable).get(0);
    }

    @Override
    public void createEntity(Airport airport) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(INSERT_AIRPORT);
                st.setString(1, airport.getName());
                st.setString(2, airport.getIATA());
            } catch (Exception e) {
                logger.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public void updateEntity(Airport airport) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(UPDATE_AIRPORT_BY_ID);
                st.setString(1, airport.getName());
                st.setString(2, airport.getIATA());
                st.setLong(3, airport.getId());
            } catch (Exception e) {
                logger.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public Airport getEntityById(long id) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_AIRPORT_BY_ID);
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
                st = con.prepareStatement(DELETE_AIRPORT_BY_ID);
                st.setLong(1, id);
            } catch (Exception e) {
                logger.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public List<Airport> getAllEntities() {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_ALL_AIRPORTS);
            } catch (Exception e) {
                logger.error(e);
            }
        };

        return getEntitiesHelper(runnable);
    }

    @Override
    protected Airport resultSetToEntity(ResultSet rs) {
        Airport airport = new Airport();

        try {
            airport.setId(rs.getLong("id"));
            airport.setName(rs.getString("name"));
            airport.setIATA(rs.getString("iata"));
        } catch (Exception e) {
            logger.error(e);
        }

        return airport;
    }
}
