package com.solvd.airport.services.impl;

import com.solvd.airport.dao.ILicenseDAO;
import com.solvd.airport.dao.IUserDAO;
import com.solvd.airport.dao.mysqlimpl.LicenseDAO;
import com.solvd.airport.dao.mysqlimpl.UserDAO;
import com.solvd.airport.models.User;
import com.solvd.airport.services.IUserService;

import java.sql.SQLException;

public class UserService implements IUserService {
    private IUserDAO userDAO = new UserDAO();
    private ILicenseDAO licenseDAO = new LicenseDAO();

    @Override
    public User getUserById(long id) throws SQLException {
        User user = (User) userDAO.getEntityById(id);
        user.setLicense(licenseDAO.getLicenseByUserId(user.getId()));

        return user;
    }
}
