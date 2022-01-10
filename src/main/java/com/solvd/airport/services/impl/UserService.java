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
    public void createUser(User user) {
        userDAO.createEntity(user);
    }

    @Override
    public User getUserById(long id) {
        return (User) userDAO.getEntityById(id);
    }

    @Override
    public void deleteUserById(long id) {
        userDAO.deleteEntityById(id);
    }

    @Override
    public List<User> getUsersByAge(int age) {
        return userDAO.getUsersByAge(age);
    }

    @Override
    public List<User> getUsersByFirstName(String firstName) {
        return userDAO.getUsersByFirstName(firstName);
    }

    @Override
    public List<User> getUsersByLastName(String lastName) {
        return userDAO.getUsersByLastName(lastName);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllEntities();
    }
}
