package com.solvd.airport.services;

import com.solvd.airport.models.User;

import java.sql.SQLException;

public interface IUserService {
    User getUserById(long id) throws SQLException;
}
