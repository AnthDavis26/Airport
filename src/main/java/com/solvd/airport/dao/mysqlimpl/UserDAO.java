package com.solvd.airport.dao.mysqlimpl;

import com.solvd.airport.dao.IUserDAO;
import com.solvd.airport.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

public class UserDAO extends AbstractMySQLDAO<User> implements IUserDAO<User> {
    private static final Logger LOGGER = LogManager.getLogger(UserDAO.class);
    private static final String INSERT_USER = "INSERT INTO users (first_name, " +
            "last_name, date_of_birth) VALUES (?,?,?)";
    private static final String UPDATE_USER_BY_ID = "UPDATE users SET first_name=?, " +
            "last_name=?, date_of_birth=? WHERE id=?";
    private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id=?";
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
    public void createEntity(User user) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(INSERT_USER);
                st.setString(1, user.getFirstName());
                st.setString(2, user.getLastName());
                st.setDate(3, Date.valueOf(user.getDateOfBirth()));
            } catch (Exception e) {
                LOGGER.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public void updateEntity(User user) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(UPDATE_USER_BY_ID);
                st.setString(1, user.getFirstName());
                st.setString(2, user.getLastName());
                st.setDate(3, Date.valueOf(user.getDateOfBirth()));
                st.setLong(4, user.getId());
            } catch (Exception e) {
                LOGGER.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }


    @Override
    public User getEntityById(long id) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_USER_BY_ID);
                st.setLong(1, id);
            } catch (Exception e) {
                LOGGER.error(e);
            }
        };

        return getEntitiesHelper(runnable).get(0);
    }

    @Override
    public void deleteEntityById(long id) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(DELETE_USER_BY_ID);
                st.setLong(1, id);
            } catch (Exception e) {
                LOGGER.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public List<User> getAllEntities() {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_ALL_USERS);
            } catch (Exception e) {
                LOGGER.error(e);
            }
        };

        return getEntitiesHelper(runnable);
    }

    @Override
    public List<User> getUsersByFirstName(String firstName) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_USERS_BY_FIRST_NAME);
                st.setString(1, firstName);
            } catch (Exception e) {
                LOGGER.error(e);
            }
        };

        return getEntitiesHelper(runnable);
    }

    @Override
    public List<User> getUsersByLastName(String LastName) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_USERS_BY_LAST_NAME);
                st.setString(1, LastName);
            } catch (Exception e) {
                LOGGER.error(e);
            }
        };

        return getEntitiesHelper(runnable);
    }

    @Override
    public List<User> getUsersByDOB(LocalDate dob) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_USERS_BY_DOB);
                st.setDate(1, Date.valueOf(dob));
            } catch (Exception e) {
                LOGGER.error(e);
            }
        };

        return getEntitiesHelper(runnable);
    }

    @Override
    public List<User> getUsersByAge(int age) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_USERS_BY_AGE);
                st.setInt(1, age);
            } catch (Exception e) {
                LOGGER.error(e);
            }
        };

        return getEntitiesHelper(runnable);
    }

    @Override
    protected User resultSetToEntity(ResultSet rs) {
        User user = new User();

        try {
            user.setId(rs.getLong("id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
        } catch (Exception e) {
            LOGGER.error(e);
        }

        return user;
    }
}
