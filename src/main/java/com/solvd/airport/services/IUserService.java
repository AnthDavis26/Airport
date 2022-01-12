package com.solvd.airport.services;

import com.solvd.airport.models.User;

import java.time.LocalDate;
import java.util.List;

public interface IUserService extends IBaseService<User> {
    User getUserById(long id);
    void deleteUserById(long id);
    List<User> getUsersByAge(int age);
    List<User> getUsersByFirstName(String firstName);
    List<User> getUsersByLastName(String lastName);
    List<User> getUsersByDateOfBirth(LocalDate dob);
}
