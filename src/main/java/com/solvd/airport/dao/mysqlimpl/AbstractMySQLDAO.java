package com.solvd.airport.dao.mysqlimpl;

import com.solvd.airport.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

abstract class AbstractMySQLDAO<T> {
    protected PreparedStatement st = null;
    protected Connection con = null;

    protected void updateEntitiesHelper(Runnable runnable) {
        final Logger LOGGER = LogManager.getLogger(this.getClass());
        this.st = null;

        try {
            this.con = ConnectionPool.getInstance().getConnection();
            runnable.run();
            this.st.executeUpdate();
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            try {
                this.st.close();
            } catch (Exception e) {
                LOGGER.error(e);
            }

            ConnectionPool.getInstance().releaseConnection(this.con);
        }
    }

    protected List<T> getEntitiesHelper(Runnable runnable) {
        List<T> entities = new ArrayList<>();
        final Logger LOGGER = LogManager.getLogger(this.getClass());
        ResultSet rs = null;

        try {
            this.con = ConnectionPool.getInstance().getConnection();
            runnable.run();
            rs = this.st.executeQuery();

            while (rs.next())
                entities.add(resultSetToEntity(rs));

        } catch (Exception e) {
            LOGGER.error(e);
        } finally {

            try {
                this.st.close();
                rs.close();
            } catch (Exception e) {
                LOGGER.error(e);
            }

            ConnectionPool.getInstance().releaseConnection(this.con);
        }

        return entities;
    }

    abstract protected T resultSetToEntity(ResultSet rs);
}
