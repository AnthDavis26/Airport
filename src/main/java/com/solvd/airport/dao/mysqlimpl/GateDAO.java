package com.solvd.airport.dao.mysqlimpl;

import com.solvd.airport.dao.IGateDAO;
import com.solvd.airport.models.Gate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.util.List;

public class GateDAO extends AbstractMySQLDAO<Gate> implements IGateDAO<Gate> {
    private static final Logger logger = LogManager.getLogger(GateDAO.class);
    private static final String INSERT_GATE = "INSERT INTO gates (name, terminal_id) VALUES (?,?)";
    private static final String UPDATE_GATE_BY_ID = "UPDATE gates SET name=?, terminal_id=? WHERE id=?";
    private static final String GET_GATE_BY_ID = "SELECT * FROM gates WHERE id=?";
    private static final String GET_ALL_GATES = "SELECT * FROM gates";
    private static final String DELETE_GATE_BY_ID = "DELETE FROM gates WHERE id=?";

    @Override
    public void createEntity(Gate gate) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(INSERT_GATE);
                st.setString(1, gate.getName());
                st.setLong(2, gate.getTerminalId());
            } catch (Exception e) {
                logger.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public void updateEntity(Gate gate) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(UPDATE_GATE_BY_ID);
                st.setString(1, gate.getName());
                st.setLong(2, gate.getTerminalId());
                st.setLong(3, gate.getId());
            } catch (Exception e) {
                logger.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public Gate getEntityById(long id) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_GATE_BY_ID);
                st.setLong(1, id);
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
                st = con.prepareStatement(DELETE_GATE_BY_ID);
                st.setLong(1, id);
            } catch (Exception e) {
                logger.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public List<Gate> getAllEntities() {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_ALL_GATES);
            } catch (Exception e) {
                logger.error(e);
            }
        };

        return getEntitiesHelper(runnable);
    }

    @Override
    protected Gate resultSetToEntity(ResultSet rs)  {
        Gate gate = new Gate();

        try {
            gate.setId(rs.getLong("id"));
            gate.setName(rs.getString("name"));
            gate.setTerminalId(rs.getLong("terminal_id"));
        } catch (Exception e) {
            logger.error(e);
        }

        return gate;
    }
}
