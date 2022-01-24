package com.solvd.airport.services;

import com.solvd.airport.models.Service;
import com.solvd.airport.models.Transaction;
import com.solvd.airport.models.User;

import java.time.LocalDate;
import java.util.List;

public interface IUserService {
    List<Transaction> getTransactionsBy(User user);
    List<Service> getServicesUsedBy(User user);
}
