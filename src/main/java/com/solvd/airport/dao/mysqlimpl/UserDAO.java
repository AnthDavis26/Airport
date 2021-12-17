package com.solvd.airport.dao.mysqlimpl;

import com.solvd.airport.dao.IUserDAO;
import com.solvd.airport.models.User;
import com.solvd.airport.utils.ConnectionPool;

import java.sql.*;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDAO extends AbstractMySQLDAO implements IUserDAO<User> {
    private static final Logger logger = LogManager.getLogger(UserDAO.class);
    private static final String GET_USER_BY_ID = "Select * from Users where id=?";

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
            st.setLong(0, id);
            rs = st.executeQuery();
        } catch (Exception e) {
            logger.info(e);
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
            user.setName(rs.getString("name"));
        }
        catch (Exception e) {
            logger.info(e);
        }

        return user;
    }

    @Override
    public void deleteEntity(long id) {

    }

    @Override
    public List<User> getUsersByAge(int age) {
        return null;
    }
}
