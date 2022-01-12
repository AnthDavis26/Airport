package com.solvd.airport.services.impl;

import com.solvd.airport.dao.IUserDAO;
import com.solvd.airport.dao.mysqlimpl.UserDAO;
import com.solvd.airport.models.User;
import com.solvd.airport.services.IUserService;

import java.time.LocalDate;
import java.util.List;

public class UserService implements IUserService {
    private IUserDAO userDAO = new UserDAO();

    @Override
    public void create(User user) {
        userDAO.createEntity(user);
    }

    @Override
    public void delete(User user) {
        userDAO.deleteEntityById(user.getId());
    }

    @Override
    public List<User> getAll() {
        return userDAO.getAllEntities();
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
    public List<User> getUsersByDateOfBirth(LocalDate dob) {
        return userDAO.getUsersByDOB(dob);
    }
}
