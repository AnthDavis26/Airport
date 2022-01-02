package com.solvd.airport.services.impl;

import com.solvd.airport.dao.IUserDAO;
import com.solvd.airport.dao.mysqlimpl.UserDAO;
import com.solvd.airport.models.User;
import com.solvd.airport.services.IUserService;

import java.sql.SQLException;
import java.util.List;

public class UserService implements IUserService {
    private IUserDAO userDAO = new UserDAO();

    @Override
    public void createUser(User user) throws SQLException {
        userDAO.createEntity(user);
    }

    @Override
    public User getUserById(long id) throws SQLException {
        return (User) userDAO.getEntityById(id);
    }

    @Override
    public void deleteUserById(long id) throws SQLException {
        userDAO.deleteEntityById(id);
    }

    @Override
    public List<User> getUsersByAge(int age) throws SQLException {
        return userDAO.getUsersByAge(age);
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        return userDAO.getAllEntities();
    }
}
