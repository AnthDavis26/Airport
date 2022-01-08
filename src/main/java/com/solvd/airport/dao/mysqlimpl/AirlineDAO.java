package com.solvd.airport.dao.mysqlimpl;

import com.solvd.airport.dao.IAirlineDAO;
import com.solvd.airport.models.Airline;
import com.solvd.airport.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirlineDAO extends AbstractMySQLDAO implements IAirlineDAO<Airline> {
    private Connection con;
    private PreparedStatement st;
    private ResultSet rs;
    private static final Logger logger = LogManager.getLogger(UserDAO.class);
    private static final String GET_AIRLINES_BY_NAME = "SELECT * FROM airlines " +
            "WHERE name=?";
    private static final String GET_AIRLINES_BY_TERMINAL_ID = "SELECT * FROM airlines " +
            "WHERE terminal_id=?";
    private static final String INSERT_AIRLINE = "INSERT INTO airlines (name, terminal_id) " +
            "VALUES(?,?)";
    private static final String UPDATE_AIRLINE_BY_ID = "UPDATE airlines SET name=?, " +
            "terminal_id=? WHERE id=?";
    private static final String GET_AIRLINE_BY_ID = "SELECT * FROM airlines WHERE id=?";
    private static final String DELETE_AIRLINE_BY_ID = "DELETE FROM airlines WHERE id=?";
    private static final String GET_ALL_AIRLINES = "SELECT * FROM airlines";

    @Override
    public void createEntity(Airline airline) throws SQLException {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(INSERT_AIRLINE);
                st.setString(1, airline.getName());
                st.setLong(2, airline.getTerminalId());
            } catch (Exception e) {
                logger.error(e);
            }
        };

        updateAirlinesHelper(runnable);
    }

    @Override
    public void updateEntity(Airline airline) throws SQLException {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(UPDATE_AIRLINE_BY_ID);
                st.setString(1, airline.getName());
                st.setLong(2, airline.getTerminalId());
                st.setLong(3, airline.getId());
            } catch (SQLException e) {
                logger.error(e);
            }
        };

        updateAirlinesHelper(runnable);
    }

    @Override
    public List<Airline> getAirlinesByName(String name) throws SQLException {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_AIRLINES_BY_NAME);
                st.setString(1, name);
            } catch (SQLException e) {
                logger.error(e);
            }
        };

        return getAirlinesHelper(runnable);
    }

    @Override
    public List<Airline> getAirlinesByTerminalId(long id) throws SQLException {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_AIRLINES_BY_TERMINAL_ID);
                st.setLong(1, id);
            } catch (SQLException e) {
                logger.error(e);
            }
        };

        return getAirlinesHelper(runnable);
    }

    @Override
    public Airline getEntityById(long id) throws SQLException {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_AIRLINE_BY_ID);
                st.setLong(1, id);
            } catch (SQLException e) {
                logger.error(e);
            }
        };

        return getAirlinesHelper(runnable).get(0);
    }

    @Override
    public void deleteEntityById(long id) throws SQLException {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(DELETE_AIRLINE_BY_ID);
                st.setLong(1, id);
            } catch (Exception e) {
                logger.error(e);
            }
        };

        updateAirlinesHelper(runnable);
    }

    @Override
    public List<Airline> getAllEntities() throws SQLException {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_ALL_AIRLINES);
            } catch (Exception e) {
                logger.error(e);
            }
        };

        return getAirlinesHelper(runnable);
    }

    private void updateAirlinesHelper(Runnable runnable) throws SQLException {
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

    private List<Airline> getAirlinesHelper(Runnable runnable)
            throws SQLException {
        List<Airline> airlines = new ArrayList<>();

        try {
            con = ConnectionPool.getInstance().getConnection();
            runnable.run();
            rs = st.executeQuery();
        } catch (Exception e) {
            logger.error(e);
        } finally {
            while (rs.next())
                airlines.add(resultSetToAirline(rs));

            rs.close();
            st.close();
            ConnectionPool.getInstance().releaseConnection(con);
        }

        return airlines;
    }

    private Airline resultSetToAirline(ResultSet rs) {
        Airline airline = new Airline();

        try {
            airline.setId(rs.getLong("id"));
            airline.setName(rs.getString("name"));
            airline.setTerminalId(rs.getLong("terminal_id"));
        } catch (Exception e) {
            logger.error(e);
        }

        return airline;
    }
}
