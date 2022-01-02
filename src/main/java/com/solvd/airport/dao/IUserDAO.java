package com.solvd.airport.dao;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO<T> extends IBaseDAO<T> {
    List<T> getUsersByAge(int age) throws SQLException;
}
