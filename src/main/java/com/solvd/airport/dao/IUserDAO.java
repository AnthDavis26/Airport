package com.solvd.airport.dao;

import com.solvd.airport.models.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface IUserDAO<T> extends IBaseDAO<T> {
    List<T> getUsersByFirstName(String firstName) throws SQLException;
    List<User> getUsersByLastName(String lastName) throws SQLException;
    List<T> getUsersByDOB(LocalDate dob) throws SQLException;
    List<T> getUsersByAge(int age) throws SQLException;
}
