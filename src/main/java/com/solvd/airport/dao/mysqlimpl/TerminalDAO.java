package com.solvd.airport.dao.mysqlimpl;

import com.solvd.airport.dao.ITerminalDAO;
import com.solvd.airport.models.Terminal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TerminalDAO extends AbstractMySQLDAO<Terminal> implements ITerminalDAO<Terminal> {
    private static final Logger LOGGER = LogManager.getLogger(TerminalDAO.class);
    private static final String INSERT_TERMINAL = "INSERT INTO terminals (name) VALUES (?)";
    private static final String GET_TERMINAL_BY_ID = "SELECT * FROM terminals WHERE id=?";
    private static final String GET_ALL_TERMINALS = "SELECT * FROM terminals";
    private static final String UPDATE_TERMINAL_BY_ID = "UPDATE terminals SET name=? WHERE id=?";
    private static final String DELETE_TERMINAL_BY_ID = "DELETE FROM terminals WHERE id=?";

    @Override
    public void createEntity(Terminal terminal) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(INSERT_TERMINAL);
                st.setString(1, terminal.getName());
            } catch (Exception e) {
                LOGGER.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public void updateEntity(Terminal terminal) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(UPDATE_TERMINAL_BY_ID);
                st.setString(1, terminal.getName());
                st.setLong(2, terminal.getId());
            } catch (Exception e) {
                LOGGER.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public Terminal getEntityById(long id){
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_TERMINAL_BY_ID);
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
                st = con.prepareStatement(DELETE_TERMINAL_BY_ID);
                st.setLong(1, id);
            } catch (Exception e) {
                LOGGER.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public List<Terminal> getAllEntities() {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_ALL_TERMINALS);
            } catch (Exception e) {
                LOGGER.error(e);
            }
        };

        return getEntitiesHelper(runnable);
    }

    @Override
    protected Terminal resultSetToEntity(ResultSet rs) {
        Terminal terminal = new Terminal();

        try {
            terminal.setId(rs.getLong("id"));
            terminal.setName(rs.getString("name"));
        } catch (Exception e) {
            LOGGER.error(e);
        }

        return terminal;
    }
}
