package com.solvd.airport.dao.mysqlimpl;

import com.solvd.airport.dao.IVehicleDAO;
import com.solvd.airport.models.User;
import com.solvd.airport.models.Vehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VehicleDAO extends AbstractMySQLDAO<Vehicle> implements IVehicleDAO<Vehicle> {
    private static final Logger LOGGER = LogManager.getLogger(VehicleDAO.class);
    private static final String INSERT_VEHICLE = "INSERT INTO vehicles (user_id, airline_id, " +
            "passenger_capacity) VALUES (?,?,?)";
    private static final String GET_VEHICLE_BY_ID = "SELECT * FROM vehicles WHERE id=?";
    private static final String GET_ALL_VEHICLES = "SELECT * FROM vehicles";
    private static final String UPDATE_VEHICLE_BY_ID = "UPDATE vehicles SET user_id=?, airline_id=?, " +
            "passenger_capacity=? WHERE id=?";
    private static final String DELETE_VEHICLE_BY_ID = "DELETE FROM vehicles WHERE id=?";

    @Override
    public void createEntity(Vehicle vehicle) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(INSERT_VEHICLE);
                st.setLong(1, vehicle.getUserId());
                st.setLong(2, vehicle.getAirlineId());
                st.setLong(3, vehicle.getPassengerCapacity());
            } catch (Exception e) {
                LOGGER.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public void updateEntity(Vehicle vehicle) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(UPDATE_VEHICLE_BY_ID);
                st.setLong(1, vehicle.getUserId());
                st.setLong(2, vehicle.getAirlineId());
                st.setLong(3, vehicle.getPassengerCapacity());
                st.setLong(4, vehicle.getId());
            } catch (Exception e) {
                LOGGER.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public Vehicle getEntityById(long id){
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_VEHICLE_BY_ID);
                st.setLong(1, id);
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        };

        return getEntitiesHelper(runnable).get(0);
    }

    @Override
    public void deleteEntityById(long id) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(DELETE_VEHICLE_BY_ID);
                st.setLong(1, id);
            } catch (Exception e) {
                LOGGER.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public List<Vehicle> getAllEntities() {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_ALL_VEHICLES);
            } catch (Exception e) {
                LOGGER.error(e);
            }
        };

        return getEntitiesHelper(runnable);
    }


    @Override
    protected Vehicle resultSetToEntity(ResultSet rs) {
        Vehicle vehicle = new Vehicle();

        try {
            vehicle.setId(rs.getLong("id"));
            vehicle.setAirlineId(rs.getLong("airline_id"));
            vehicle.setUserId(rs.getLong("user_id"));
            vehicle.setPassengerCapacity(rs.getLong("passenger_capacity"));
        } catch (Exception e) {
            LOGGER.error(e);
        }

        return vehicle;
    }
}
