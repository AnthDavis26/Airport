package com.solvd.airport.dao.mysqlimpl;

import com.solvd.airport.dao.ISeatDAO;
import com.solvd.airport.models.Seat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SeatDAO extends AbstractMySQLDAO<Seat> implements ISeatDAO<Seat> {
    @Override
    public void createEntity(Seat entity) {

    }

    @Override
    public void updateEntity(Seat entity) {

    }

    @Override
    public Seat getEntityById(long id) {
        return null;
    }

    @Override
    public void deleteEntityById(long id) {

    }

    @Override
    public List<Seat> getAllEntities() {
        return null;
    }

    @Override
    protected Seat resultSetToEntity(ResultSet rs) {
        return null;
    }
}
