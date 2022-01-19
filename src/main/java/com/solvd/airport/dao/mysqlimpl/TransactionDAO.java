package com.solvd.airport.dao.mysqlimpl;

import com.solvd.airport.dao.ITransactionDAO;
import com.solvd.airport.models.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TransactionDAO extends AbstractMySQLDAO<Transaction>
        implements ITransactionDAO<Transaction> {
    private static final Logger LOGGER = LogManager.getLogger(TransactionDAO.class);
    private static final String INSERT_TRANSACTION = "INSERT INTO transactions (user_id) VALUES (?)";
    private static final String GET_TRANSACTION_BY_ID = "SELECT * FROM transactions WHERE id=?";
    private static final String GET_ALL_TRANSACTIONS = "SELECT * FROM transactions";
    private static final String UPDATE_TRANSACTION_BY_ID = "UPDATE transactions SET user_id=? WHERE id=?";
    private static final String DELETE_TRANSACTION_BY_ID = "DELETE FROM transactions WHERE id=?";

    @Override
    public void createEntity(Transaction transaction) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(INSERT_TRANSACTION);
                st.setLong(1, transaction.getUserId());
            } catch (Exception e) {
                LOGGER.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public void updateEntity(Transaction transaction) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(UPDATE_TRANSACTION_BY_ID);
                st.setLong(1, transaction.getUserId());
                st.setLong(2, transaction.getId());
            } catch (Exception e) {
                LOGGER.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public Transaction getEntityById(long id){
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_TRANSACTION_BY_ID);
                st.setLong(1, id);
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        };

        return getEntitiesHelper(runnable).get(0);
    }

    @Override
    public void deleteEntityById(long id) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(DELETE_TRANSACTION_BY_ID);
                st.setLong(1, id);
            } catch (Exception e) {
                LOGGER.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public List<Transaction> getAllEntities() {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_ALL_TRANSACTIONS);
            } catch (Exception e) {
                LOGGER.error(e);
            }
        };

        return getEntitiesHelper(runnable);
    }

    @Override
    protected Transaction resultSetToEntity(ResultSet rs) {
        Transaction transaction = new Transaction();

        try {
            transaction.setId(rs.getLong("id"));
            transaction.setUserId(rs.getLong("user_id"));
        } catch (Exception e) {
            LOGGER.error(e);
        }

        return transaction;
    }
}
