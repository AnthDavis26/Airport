package com.solvd.airport.dao.mysqlimpl;

import com.solvd.airport.dao.IUserDAO;
import com.solvd.airport.models.User;
import com.solvd.airport.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstractMySQLDAO implements IUserDAO<User> {
    private Connection con;
    private PreparedStatement st;
    private ResultSet rs;
    private static final Logger logger = LogManager.getLogger(UserDAO.class);
    private static final String INSERT_USER = "INSERT INTO users (first_name, " +
            "last_name, date_of_birth) VALUES (?,?,?)";
    private static final String UPDATE_USER_BY_ID = "UPDATE users SET first_name=?, " +
            "last_name=?, date_of_birth=? WHERE id=?";
    private static final String GET_USER_BY_ID = "SELECT * FROM users where id=?";
    private static final String GET_USERS_BY_AGE = "SELECT * FROM users " +
            "WHERE TIMESTAMPDIFF(YEAR, date_of_birth, CURDATE())=?";
    private static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id=?";
    private static final String GET_USERS_BY_FIRST_NAME = "SELECT * FROM users " +
            "WHERE first_name=?";
    private static final String GET_USERS_BY_LAST_NAME = "SELECT * FROM users " +
            "WHERE last_name=?";
    private static final String GET_USERS_BY_DOB = "SELECT * FROM users " +
            "WHERE date_of_birth=?";
    private static final String GET_ALL_USERS = "SELECT * FROM users";

    @Override
    public void createEntity(User user) throws SQLException {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(INSERT_USER);
                st.setString(1, user.getFirstName());
                st.setString(2, user.getLastName());
                st.setDate(3, Date.valueOf(user.getDateOfBirth()));
            } catch (Exception e) {
                logger.error(e);
            }
        };

        updateUsersHelper(runnable);
    }

    @Override
    public void updateEntity(User user) throws SQLException {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(UPDATE_USER_BY_ID);
                st.setString(1, user.getFirstName());
                st.setString(2, user.getLastName());
                st.setDate(3, Date.valueOf(user.getDateOfBirth()));
                st.setLong(4, user.getId());
            } catch (Exception e) {
                logger.error(e);
            }
        };

        updateUsersHelper(runnable);
    }


    @Override
    public User getEntityById(long id) throws SQLException {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_USER_BY_ID);
                st.setLong(1, id);
            } catch (Exception e) {
                logger.error(e);
            }
        };

        return getUsersHelper(runnable).get(0);
    }

    @Override
    public void deleteEntityById(long id) throws SQLException {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(DELETE_USER_BY_ID);
                st.setLong(1, id);
            } catch (Exception e) {
                logger.error(e);
            }
        };

        updateUsersHelper(runnable);
    }

    @Override
    public List<User> getAllEntities() throws SQLException {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_ALL_USERS);
            } catch (Exception e) {
                logger.error(e);
            }
        };

        return getUsersHelper(runnable);
    }

    @Override
    public List<User> getUsersByFirstName(String firstName) throws SQLException {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_USERS_BY_FIRST_NAME);
                st.setString(1, firstName);
            } catch (Exception e) {
                logger.error(e);
            }
        };

        return getUsersHelper(runnable);
    }

    @Override
    public List<User> getUsersByLastName(String LastName) throws SQLException {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_USERS_BY_LAST_NAME);
                st.setString(1, LastName);
            } catch (Exception e) {
                logger.error(e);
            }
        };

        return getUsersHelper(runnable);
    }

    @Override
    public List<User> getUsersByDOB(LocalDate dob) throws SQLException {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_USERS_BY_DOB);
                st.setDate(1, Date.valueOf(dob));
            } catch (Exception e) {
                logger.error(e);
            }
        };

        return getUsersHelper(runnable);
    }

    @Override
    public List<User> getUsersByAge(int age) throws SQLException {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_USERS_BY_AGE);
                st.setInt(1, age);
            } catch (Exception e) {
                logger.error(e);
            }
        };

        return getUsersHelper(runnable);
    }

    private List<User> getUsersHelper(Runnable runnable) throws SQLException {
        List<User> users = new ArrayList<>();

        try {
            con = ConnectionPool.getInstance().getConnection();
            runnable.run();
            st.executeQuery();
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

    private void updateUsersHelper(Runnable runnable) throws SQLException {
        try {
            con = ConnectionPool.getInstance().getConnection();
            runnable.run();
            st.executeUpdate();
        } catch (Exception e) {
            logger.error(e);
        } finally {
            st.close();
            ConnectionPool.getInstance().releaseConnection(con);
        }
    }

    private User resultSetToUser(ResultSet rs) {
        User user = new User();

        try {
            user.setId(rs.getLong("id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
        }
        catch (Exception e) {
            logger.error(e);
        }

        return user;
    }
}
