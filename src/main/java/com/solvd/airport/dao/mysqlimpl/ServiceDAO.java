package com.solvd.airport.dao.mysqlimpl;

import com.solvd.airport.dao.IServiceDAO;
import com.solvd.airport.models.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ServiceDAO extends AbstractMySQLDAO<Service> implements IServiceDAO<Service> {
    @Override
    public void createEntity(Service entity) {

    }

    @Override
    public void updateEntity(Service entity) {

    }

    @Override
    public Service getEntityById(long id) {
        return null;
    }

    @Override
    public void deleteEntityById(long id) {

    }

    @Override
    public List<Service> getAllEntities() {
        return null;
    }

    @Override
    protected Service resultSetToEntity(ResultSet rs) {
        return null;
    }
}
