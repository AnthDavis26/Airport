package com.solvd.airport.dao.mysqlimpl;

import com.solvd.airport.dao.ITicketDAO;
import com.solvd.airport.models.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TicketDAO extends AbstractMySQLDAO<Ticket> implements ITicketDAO<Ticket> {
    @Override
    public void createEntity(Ticket entity) {

    }

    @Override
    public void updateEntity(Ticket entity) {

    }

    @Override
    public Ticket getEntityById(long id) {
        return null;
    }

    @Override
    public void deleteEntityById(long id) {

    }

    @Override
    public List<Ticket> getAllEntities() {
        return null;
    }

    @Override
    protected Ticket resultSetToEntity(ResultSet rs) {
        return null;
    }
}
