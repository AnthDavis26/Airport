package com.solvd.airport.dao;

import com.solvd.airport.models.User;

import java.util.List;

public interface IVehicleDAO<T> extends IBaseDAO<T>  {
    List<User> getUsersByLicenseId(long id);
}
