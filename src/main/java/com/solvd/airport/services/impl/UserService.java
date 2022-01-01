package com.solvd.airport.services.impl;

import com.solvd.airport.dao.IUserDAO;
import com.solvd.airport.dao.mysqlimpl.UserDAO;
import com.solvd.airport.models.User;
import com.solvd.airport.services.IUserService;

import java.sql.SQLException;

public class UserService implements IUserService {
    private IUserDAO userDAO = new UserDAO();

    @Override
    public User getUserById(long id) throws SQLException {
        return (User) userDAO.getEntityById(id);
    }
}
