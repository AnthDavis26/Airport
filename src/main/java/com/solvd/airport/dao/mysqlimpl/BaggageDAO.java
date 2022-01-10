package com.solvd.airport.dao.mysqlimpl;

import com.solvd.airport.dao.IBaggageDAO;
import com.solvd.airport.models.Baggage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BaggageDAO extends AbstractMySQLDAO<Baggage> implements IBaggageDAO<Baggage> {
    private static final Logger logger = LogManager.getLogger(BaggageDAO.class);
    private static final String GET_BAGGAGE_BY_ID = "SELECT * FROM baggage WHERE id = ?";
    private static final String GET_BAGGAGE_BY_PASSENGER_ID = "SELECT * FROM baggage WHERE passenger_id = ?";
    private static final String INSERT_BAGGAGE = "INSERT INTO baggage (passenger_id) VALUES(?)";
    private static final String UPDATE_BAGGAGE_BY_ID = "UPDATE baggage SET passenger_id = ? WHERE id=?";
    private static final String DELETE_BAGGAGE_BY_ID = "DELETE FROM baggage WHERE id=?";
    private static final String GET_ALL_BAGGAGE = "SELECT * FROM baggage";

    @Override
    public Baggage getBaggageByPassengerId(long id) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_BAGGAGE_BY_PASSENGER_ID);
                st.setLong(1, id);
            } catch (SQLException e) {
                logger.error(e);
            }
        };

        return getEntitiesHelper(runnable).get(0);
    }

    @Override
    public void createEntity(Baggage baggage) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(INSERT_BAGGAGE);
            } catch (Exception e) {
                logger.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public void updateEntity(Baggage baggage) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(UPDATE_BAGGAGE_BY_ID);
                st.setLong(1, baggage.getPassengerId());
                st.setLong(2, baggage.getId());
            } catch (SQLException e) {
                logger.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public Baggage getEntityById(long id) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_BAGGAGE_BY_ID);
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
                st = con.prepareStatement(DELETE_BAGGAGE_BY_ID);
                st.setLong(1, id);
            } catch (Exception e) {
                logger.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public List<Baggage> getAllEntities() {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_ALL_BAGGAGE);
            } catch (Exception e) {
                logger.error(e);
            }
        };

        return getEntitiesHelper(runnable);
    }

    @Override
    public Baggage resultSetToEntity(ResultSet rs) {
        Baggage baggage = new Baggage();

        try {
            baggage.setId(rs.getLong("id"));
            baggage.setPassengerId(rs.getLong("passenger_id"));
        } catch (Exception e) {
            logger.error(e);
        }

        return baggage;
    }
}
