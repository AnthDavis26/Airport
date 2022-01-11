package com.solvd.airport.dao.mysqlimpl;

import com.solvd.airport.dao.ISeatDAO;
import com.solvd.airport.models.Seat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SeatDAO extends AbstractMySQLDAO<Seat> implements ISeatDAO<Seat> {
    private static final Logger logger = LogManager.getLogger(SeatDAO.class);
    private static final String INSERT_SEAT = "INSERT INTO seats (vehicle_id) VALUES (?)";
    private static final String UPDATE_SEAT_BY_ID = "UPDATE seats SET vehicle_id=? WHERE id=?";
    private static final String DELETE_SEAT_BY_ID = "DELETE FROM seats WHERE id=?";
    private static final String GET_SEAT_BY_ID = "SELECT * FROM seats WHERE id=?";
    private static final String GET_ALL_SEATS = "SELECT * FROM seats";

    @Override
    public void createEntity(Seat seat) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(INSERT_SEAT);
                st.setLong(1, seat.getVehicleId());
            } catch (Exception e) {
                logger.error(e);
            }
        };

        updateEntitiesHelper(runnable);

    }

    @Override
    public void updateEntity(Seat seat) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(UPDATE_SEAT_BY_ID);
                st.setLong(1, seat.getVehicleId());
                st.setLong(2, seat.getId());
            } catch (Exception e) {
                logger.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public Seat getEntityById(long id){
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_SEAT_BY_ID);
                st.setLong(1, id);
            } catch (SQLException e) {
                logger.error(e);
            }
        };

        return getEntitiesHelper(runnable).get(0);
    }

    @Override
    public void deleteEntityById(long id) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(DELETE_SEAT_BY_ID);
                st.setLong(1, id);
            } catch (Exception e) {
                logger.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public List<Seat> getAllEntities(){
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_ALL_SEATS);
            } catch (SQLException e) {
                logger.error(e);
            }
        };

        return getEntitiesHelper(runnable);
    }

    @Override
    protected Seat resultSetToEntity(ResultSet rs) {
        Seat seat = new Seat();

        try {
            seat.setId(rs.getLong("id"));
            seat.setVehicleId(rs.getLong("vehicle_id"));
        } catch (Exception e) {
            logger.error(e);
        }

        return seat;
    }
}
