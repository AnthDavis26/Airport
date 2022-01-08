package com.solvd.airport.services;

import com.solvd.airport.models.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserService {
    void createUser(User user) throws SQLException;
    User getUserById(long id) throws SQLException;
    void deleteUserById(long id) throws SQLException;
    List<User> getUsersByAge(int age) throws SQLException;
    List<User> getUsersByFirstName(String firstName) throws SQLException;
    List<User> getUsersByLastName(String lastName) throws SQLException;
    List<User> getAllUsers() throws SQLException;
}
