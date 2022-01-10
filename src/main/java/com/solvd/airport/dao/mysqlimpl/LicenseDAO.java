package com.solvd.airport.dao.mysqlimpl;

import com.solvd.airport.dao.ILicenseDAO;
import com.solvd.airport.models.License;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.util.List;

public class LicenseDAO extends AbstractMySQLDAO<License> implements ILicenseDAO<License> {
    private static final Logger logger = LogManager.getLogger(UserDAO.class);
    private static final String GET_LICENSE_BY_ID = "SELECT * FROM licenses WHERE id=?";
    private static final String CREATE_LICENSE = "INSERT INTO licenses (users_id) VALUES(?)";
    private static final String UPDATE_LICENSE_BY_ID = "UPDATE licenses SET users_id=? WHERE id=?";
    private static final String DELETE_LICENSE_BY_ID = "DELETE FROM licenses WHERE id=?";
    private static final String GET_ALL_LICENSES = "SELECT * FROM licenses";

    @Override
    public void createEntity(License license) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(CREATE_LICENSE);
                st.setLong(1, license.getUserId());
            } catch (Exception e) {
                logger.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public void updateEntity(License license) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(UPDATE_LICENSE_BY_ID);
                st.setLong(1, license.getUserId());
                st.setLong(2, license.getId());
            } catch (Exception e) {
                logger.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public License getEntityById(long id) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_LICENSE_BY_ID);
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
                st = con.prepareStatement(DELETE_LICENSE_BY_ID);
                st.setLong(1, id);
            } catch (Exception e) {
                logger.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public List<License> getAllEntities() {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_ALL_LICENSES);
            } catch (Exception e) {
                logger.error(e);
            }
        };

        return getEntitiesHelper(runnable);
    }

    @Override
    protected License resultSetToEntity(ResultSet rs) {
        License license = new License();

        try {
            license.setId(rs.getLong("id"));
            license.setUserId(rs.getLong("user_id"));
        } catch (Exception e) {
            logger.error(e);
        }

        return license;
    }
}
