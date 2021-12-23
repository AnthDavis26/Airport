package com.solvd.airport.dao.mysqlimpl;

import com.solvd.airport.dao.ILicenseDAO;
import com.solvd.airport.models.License;
import com.solvd.airport.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LicenseDAO extends AbstractMySQLDAO implements ILicenseDAO<License> {
    private static final Logger logger = LogManager.getLogger(UserDAO.class);
    private static final String GET_LICENSE_BY_ID = "SELECT * FROM Licenses WHERE id=?";
    private static final String CREATE_LICENSE = "INSERT INTO Licenses VALUES()";

    @Override
    public void createEntity(License entity) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;

        try {
            con = ConnectionPool.getInstance().getConnection();
            st = con.prepareStatement(CREATE_LICENSE);
            st.executeUpdate();
        } catch (Exception e) {
            logger.error(e);
        } finally {
            st.close();
            ConnectionPool.getInstance().releaseConnection(con);
        }
    }

    @Override
    public void updateEntity(License entity) {

    }

    @Override
    public License getEntityById(long id) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = ConnectionPool.getInstance().getConnection();
            st = con.prepareStatement(GET_LICENSE_BY_ID);
            st.setLong(1, id);
            rs = st.executeQuery();
        } catch (Exception e) {
            logger.error(e);
        } finally {
            rs.close();
            st.close();
            ConnectionPool.getInstance().releaseConnection(con);
        }

        return resultSetToLicense(rs);
    }

    private License resultSetToLicense(ResultSet rs) {
        License license = new License();

        try {
            license.setId(rs.getLong("id"));
        }
        catch (Exception e) {
            logger.error(e);
        }

        return license;
    }


    @Override
    public void deleteEntityById(long id) throws SQLException {

    }

    @Override
    public License getLicenseByUserId(Long id) throws SQLException {
       return getEntityById(new UserDAO().getEntityById(id).getLicense().getId());
    }
}
