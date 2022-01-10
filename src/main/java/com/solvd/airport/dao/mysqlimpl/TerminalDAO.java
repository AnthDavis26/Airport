package com.solvd.airport.dao.mysqlimpl;

import com.solvd.airport.dao.ITerminalDAO;
import com.solvd.airport.models.Terminal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TerminalDAO extends AbstractMySQLDAO<Terminal> implements ITerminalDAO<Terminal> {
    @Override
    public void createEntity(Terminal entity) {

    }

    @Override
    public void updateEntity(Terminal entity) {

    }

    @Override
    public Terminal getEntityById(long id) {
        return null;
    }

    @Override
    public void deleteEntityById(long id) {

    }

    @Override
    public List<Terminal> getAllEntities() {
        return null;
    }

    @Override
    protected Terminal resultSetToEntity(ResultSet rs) {
        return null;
    }
}
