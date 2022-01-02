package com.solvd.airport.dao.mysqlimpl;

import com.solvd.airport.dao.IUserDAO;
import com.solvd.airport.models.User;
import com.solvd.airport.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstractMySQLDAO implements IUserDAO<User> {
    private static final Logger logger = LogManager.getLogger(UserDAO.class);
    private static final String INSERT_USER = "INSERT INTO users (first_name, " +
            "last_name, birthday) VALUES (?,?,?)";
    private static final String UPDATE_USER_BY_ID = "UPDATE Users SET first_name=?, " +
            "last_name=?, birthday=? WHERE id=?";
    private static final String GET_USER_BY_ID = "SELECT * FROM users where id=?";
    private static final String GET_USERS_BY_AGE = "SELECT * FROM users " +
            "WHERE TIMESTAMPDIFF(YEAR, birthday, CURDATE())=?";
    private static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id=?";

    @Override
    public void createEntity(User user) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;

        try {
            con = ConnectionPool.getInstance().getConnection();
            st = con.prepareStatement(INSERT_USER);
            st.setString(1, user.getFirstName());
            st.setString(2, user.getLastName());
            st.setDate(3, Date.valueOf(user.getBirthday()));
            st.executeUpdate();
        } catch (Exception e) {
            logger.error(e);
        } finally {
            st.close();
            ConnectionPool.getInstance().releaseConnection(con);
        }
    }

    @Override
    public void updateEntity(User user) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;

        try {
            con = ConnectionPool.getInstance().getConnection();
            st = con.prepareStatement(UPDATE_USER_BY_ID);
            st.setString(1, user.getFirstName());
            st.setString(2, user.getLastName());
            st.setDate(3, Date.valueOf(user.getBirthday()));
            st.setLong(4, user.getId());
            st.executeUpdate();
        } catch (Exception e) {
            logger.error(e);
        } finally {
            st.close();
            ConnectionPool.getInstance().releaseConnection(con);
        }
    }

    @Override
    public User getEntityById(long id) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        User user;

        try {
            con = ConnectionPool.getInstance().getConnection();
            st = con.prepareStatement(GET_USER_BY_ID);
            st.setLong(1, id);
            rs = st.executeQuery();
        } catch (Exception e) {
            logger.error(e);
        } finally {
            rs.next();
            user = resultSetToUser(rs);
            rs.close();
            st.close();
            ConnectionPool.getInstance().releaseConnection(con);
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

    @Override
    public List<User> getUsersByAge(int age) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<>();

        try {
            con = ConnectionPool.getInstance().getConnection();
            st = con.prepareStatement(GET_USERS_BY_AGE);
            st.setInt(1, age);
            rs = st.executeQuery();
        } catch (Exception e) {
            logger.error(e);
        } finally {
            while (rs.next())
                users.add(resultSetToUser(rs));

            rs.close();
            st.close();
            ConnectionPool.getInstance().releaseConnection(con);
        }

        return users;
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
}
