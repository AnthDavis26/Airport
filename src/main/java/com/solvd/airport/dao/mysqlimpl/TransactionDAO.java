package com.solvd.airport.dao.mysqlimpl;

import com.solvd.airport.dao.ITransactionDAO;
import com.solvd.airport.models.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TransactionDAO extends AbstractMySQLDAO<Transaction>
        implements ITransactionDAO<Transaction> {
    @Override
    public void createEntity(Transaction entity) {

    }

    @Override
    public void updateEntity(Transaction entity) {

    }

    @Override
    public Transaction getEntityById(long id) {
        return null;
    }

    @Override
    public void deleteEntityById(long id) {

    }

    @Override
    public List<Transaction> getAllEntities() {
        return null;
    }

    @Override
    protected Transaction resultSetToEntity(ResultSet rs) {
        return null;
    }
}
