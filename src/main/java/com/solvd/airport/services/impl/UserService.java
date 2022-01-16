package com.solvd.airport.services.impl;

import com.solvd.airport.dao.IUserDAO;
import com.solvd.airport.dao.mysqlimpl.UserDAO;
import com.solvd.airport.models.User;
import com.solvd.airport.services.IUserService;

import java.time.LocalDate;
import java.util.List;

public class UserService implements IUserService {
    private IUserDAO userDAO = new UserDAO();
}
