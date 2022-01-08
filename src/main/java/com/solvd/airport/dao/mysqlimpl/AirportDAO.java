package com.solvd.airport.dao.mysqlimpl;

import com.solvd.airport.dao.IAirportDAO;
import com.solvd.airport.models.Airport;
import com.solvd.airport.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirportDAO extends AbstractMySQLDAO implements IAirportDAO<Airport> {
    private Connection con;
    private PreparedStatement st;
    private ResultSet rs;
    private static final Logger logger = LogManager.getLogger(AirportDAO.class);
    private static final String GET_AIRPORT_BY_ID = "SELECT * FROM airports WHERE id = ?";
    private static final String GET_AIRPORTS_BY_NAME = "SELECT * FROM airports WHERE name = ?";
    private static final String GET_AIRPORTS_BY_IATA = "SELECT * FROM airports WHERE iata = ?";
    private static final String INSERT_AIRPORT = "INSERT INTO airports (name, iata) VALUES(?,?)";
    private static final String UPDATE_AIRPORT_BY_ID = "UPDATE airports SET name=?, iata=? " +
            "WHERE id=?";
    private static final String DELETE_AIRPORT_BY_ID = "DELETE FROM airports WHERE id=?";
    private static final String GET_ALL_AIRPORTS = "SELECT * FROM airports";

    @Override
    public Airport getAirportByName(String name) throws SQLException {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_AIRPORTS_BY_NAME);
                st.setString(1, name);
            } catch (Exception e) {
                logger.error(e);
            }
        };

        return getAirportsHelper(runnable).get(0);
    }

    @Override
    public Airport getAirportByIATA(String iata) throws SQLException {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_AIRPORTS_BY_IATA);
                st.setString(1, iata);
            } catch (Exception e) {
                logger.error(e);
            }
        };

        return getAirportsHelper(runnable).get(0);
    }

    @Override
    public void createEntity(Airport airport) throws SQLException {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(INSERT_AIRPORT);
                st.setString(1, airport.getName());
                st.setString(2, airport.getIATA());
            } catch (Exception e) {
                logger.error(e);
            }
        };

        updateAirportsHelper(runnable);
    }

    @Override
    public void updateEntity(Airport airport) throws SQLException {
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

        updateAirportsHelper(runnable);
    }

    @Override
    public Airport getEntityById(long id) throws SQLException {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_AIRPORT_BY_ID);
                st.setLong(1, id);
            } catch (Exception e) {
                logger.error(e);
            }
        };

        getAirportsHelper(runnable).get(0);
    }

    @Override
    public void deleteEntityById(long id) throws SQLException {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(DELETE_AIRPORT_BY_ID);
                st.setLong(1, id);
            } catch (Exception e) {
                logger.error(e);
            }
        };

        updateAirportsHelper(runnable);
    }

    @Override
    public List<Airport> getAllEntities() throws SQLException {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_ALL_AIRPORTS);
            } catch (Exception e) {
                logger.error(e);
            }
        };

        return getAirportsHelper(runnable);
    }

    private void updateAirportsHelper(Runnable runnable) throws SQLException {
        try {
            con = ConnectionPool.getInstance().getConnection();
            runnable.run();
            st.executeUpdate();
        } catch (Exception e) {
            logger.error(e);
        } finally {
            st.close();
            ConnectionPool.getInstance().releaseConnection(con);
        }
    }

    private List<Airport> getAirportsHelper(Runnable runnable)
            throws SQLException {
        List<Airport> airports = new ArrayList<>();

        try {
            con = ConnectionPool.getInstance().getConnection();
            runnable.run();
            rs = st.executeQuery();
        } catch (Exception e) {
            logger.error(e);
        } finally {
            while (rs.next())
                airports.add(resultSetToAirport(rs));

            rs.close();
            st.close();
            ConnectionPool.getInstance().releaseConnection(con);
        }

        return airports;
    }

    private Airport resultSetToAirport(ResultSet rs) {
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
