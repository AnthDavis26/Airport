package com.solvd.airport.dao;

import java.util.List;

public interface ITransactionDAO<T> extends IBaseDAO<T>  {
    List<T> getTransactionsByUserID(long id);
}
