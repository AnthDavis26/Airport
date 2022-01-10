package com.solvd.airport.dao.mysqlimpl;

import com.solvd.airport.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

abstract class AbstractMySQLDAO<T> {
    protected PreparedStatement st = null;
    protected Connection con = null;

    protected void updateEntitiesHelper(Runnable runnable) {
        Logger logger = LogManager.getLogger(this.getClass());

        try {
            logger.info("test");
            logger.info(con);
            logger.info(this.con);
            this.con = ConnectionPool.getInstance().getConnection();
            runnable.run();
            this.st.executeUpdate();
            this.st.close();
        } catch (Exception e) {
            logger.error(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(this.con);
        }
    }

    protected List<T> getEntitiesHelper(Runnable runnable) {
        List<T> entities = new ArrayList<>();
        Logger logger = LogManager.getLogger(this.getClass());
        ResultSet rs;

        try {
            this.con = ConnectionPool.getInstance().getConnection();
            runnable.run();
            rs = this.st.executeQuery();

            while (rs.next())
                entities.add(resultSetToEntity(rs));

            rs.close();
            this.st.close();
        } catch (Exception e) {
            logger.error(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(this.con);
        }

        return entities;
    }

    abstract protected T resultSetToEntity(ResultSet rs);
}
