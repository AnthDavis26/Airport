package com.solvd.airport.dao;

import com.solvd.airport.models.License;

import java.sql.SQLException;

public interface ILicenseDAO<T> extends IBaseDAO<T> {
    License getLicenseByUserId(Long id) throws SQLException;
}
