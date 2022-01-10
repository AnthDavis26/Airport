package com.solvd.airport.dao.mysqlimpl;

import com.solvd.airport.dao.IVehicleDAO;
import com.solvd.airport.models.User;
import com.solvd.airport.models.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VehicleDAO extends AbstractMySQLDAO<Vehicle> implements IVehicleDAO<Vehicle> {
    @Override
    public void createEntity(Vehicle entity) {

    }

    @Override
    public void updateEntity(Vehicle entity) {

    }

    @Override
    public Vehicle getEntityById(long id) {
        return null;
    }

    @Override
    public void deleteEntityById(long id) {

    }

    @Override
    public List<Vehicle> getAllEntities() {
        return null;
    }

    @Override
    public List<User> getUsersByLicenseId(long id) {
        return null;
    }

    @Override
    protected Vehicle resultSetToEntity(ResultSet rs) {
        return null;
    }
}
