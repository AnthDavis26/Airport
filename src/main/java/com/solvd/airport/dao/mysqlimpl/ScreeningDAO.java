package com.solvd.airport.dao.mysqlimpl;

import com.solvd.airport.dao.IScreeningDAO;
import com.solvd.airport.models.Screening;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ScreeningDAO extends AbstractMySQLDAO<Screening> implements IScreeningDAO<Screening> {
    @Override
    public void createEntity(Screening entity) {

    }

    @Override
    public void updateEntity(Screening entity) {

    }

    @Override
    public Screening getEntityById(long id) {
        return null;
    }

    @Override
    public void deleteEntityById(long id) {

    }

    @Override
    public List<Screening> getAllEntities() {
        return null;
    }

    @Override
    protected Screening resultSetToEntity(ResultSet rs) {
        return null;
    }
}
