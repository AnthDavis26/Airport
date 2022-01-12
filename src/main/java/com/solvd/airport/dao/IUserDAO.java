package com.solvd.airport.dao;

import com.solvd.airport.models.User;

import java.time.LocalDate;
import java.util.List;

public interface IUserDAO<T> extends IBaseDAO<T> {
    List<T> getUsersByFirstName(String firstName);
    List<T> getUsersByLastName(String lastName);
    List<T> getUsersByDOB(LocalDate dob);
    List<T> getUsersByAge(int age);
}
