package com.solvd.airport.dao.mysqlimpl;

import com.solvd.airport.dao.IServiceDAO;
import com.solvd.airport.models.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.util.List;

public class ServiceDAO extends AbstractMySQLDAO<Service> implements IServiceDAO<Service> {
    private static final Logger logger = LogManager.getLogger(ServiceDAO.class);
    private static final String INSERT_SERVICE = "INSERT INTO services (name, transaction_id) " +
            "VALUES (?,?)";
    private static final String UPDATE_SERVICE_BY_ID = "UPDATE services SET name=?, transaction_id=? " +
            "WHERE id=?";
    private static final String DELETE_SERVICE_BY_ID = "DELETE FROM services WHERE id=?";
    private static final String GET_SERVICE_BY_ID = "SELECT * FROM services WHERE id=?";
    private static final String GET_ALL_SERVICES = "SELECT * FROM services";

    @Override
    public void createEntity(Service service) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(INSERT_SERVICE);
                st.setString(1, service.getName());
                st.setLong(2, service.getTransactionId());
            } catch (Exception e) {
                logger.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public void updateEntity(Service service) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(UPDATE_SERVICE_BY_ID);
                st.setString(1, service.getName());
                st.setLong(2, service.getTransactionId());
                st.setLong(3, service.getId());
            } catch (Exception e) {
                logger.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public Service getEntityById(long id){
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_SERVICE_BY_ID);
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
                st = con.prepareStatement(DELETE_SERVICE_BY_ID);
                st.setLong(1, id);
            } catch (Exception e) {
                logger.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public List<Service> getAllEntities() {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_ALL_SERVICES);
            } catch (Exception e) {
                logger.error(e);
            }
        };

        return getEntitiesHelper(runnable);
    }

    @Override
    protected Service resultSetToEntity(ResultSet rs) {
        Service service = new Service();

        try {
            service.setId(rs.getLong("id"));
            service.setName(rs.getString("name"));
            service.setTransactionId(rs.getLong("transaction_id"));
        } catch (Exception e) {
            logger.error(e);
        }

        return service;
    }
}
