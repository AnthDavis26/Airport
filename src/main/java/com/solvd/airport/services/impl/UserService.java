package com.solvd.airport.services.impl;

import com.solvd.airport.dao.IServiceDAO;
import com.solvd.airport.dao.ITransactionDAO;
import com.solvd.airport.dao.IUserDAO;
import com.solvd.airport.dao.mysqlimpl.ServiceDAO;
import com.solvd.airport.dao.mysqlimpl.TransactionDAO;
import com.solvd.airport.dao.mysqlimpl.UserDAO;
import com.solvd.airport.models.Service;
import com.solvd.airport.models.Transaction;
import com.solvd.airport.models.User;
import com.solvd.airport.services.IUserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserService implements IUserService {
    private IUserDAO userDAO = new UserDAO();
    private ITransactionDAO transactionDAO = new TransactionDAO();
    private IServiceDAO serviceDAO = new ServiceDAO();

    @Override
    public List<Transaction> getTransactionsBy(User user) {
        return transactionDAO.getTransactionsByUserID(user.getId());
    }

    @Override
    public List<Service> getServicesUsedBy(User user) {
        Set<Service> services = new HashSet<>();
        List<Transaction> transactions = getTransactionsBy(user);

        for (Transaction tr : transactions)
            services.add((Service) serviceDAO.getServiceById(tr.getServiceId()));

        return services.stream().toList();
    }
}
