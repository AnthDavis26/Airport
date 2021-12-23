package com.solvd.airport.dao.mysqlimpl;

import com.solvd.airport.dao.IUserDAO;
import com.solvd.airport.models.User;
import com.solvd.airport.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends AbstractMySQLDAO implements IUserDAO<User> {
    private static final Logger logger = LogManager.getLogger(UserDAO.class);
    private static final String GET_USER_BY_ID = "Select * from Users where id=?";
    private static final String DELETE_USER_BY_ID = "Delete from Users where id=?";

    @Override
    public void createEntity(User entity) {

    }

    @Override
    public void updateEntity(User entity) {

    }

    @Override
    public User getEntityById(long id) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = ConnectionPool.getInstance().getConnection();
            st = con.prepareStatement(GET_USER_BY_ID);
            st.setLong(1, id);
            rs = st.executeQuery();
        } catch (Exception e) {
            logger.error(e);
        } finally {
            rs.close();
            st.close();
            ConnectionPool.getInstance().releaseConnection(con);
        }

        return resultSetToUser(rs);
    }

    private User resultSetToUser(ResultSet rs) {
        User user = new User();

        try {
            user.setId(rs.getLong("id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setBirthday(rs.getDate("birthday").toLocalDate());
        }
        catch (Exception e) {
            logger.error(e);
        }

        return user;
    }

    @Override
    public void deleteEntityById(long id) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;

        try {
            con = ConnectionPool.getInstance().getConnection();
            st = con.prepareStatement(DELETE_USER_BY_ID);
            st.setLong(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            logger.error(e);
        } finally {
            st.close();
            ConnectionPool.getInstance().releaseConnection(con);
        }
    }
}
