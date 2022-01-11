package com.solvd.airport.dao.mysqlimpl;

import com.solvd.airport.dao.IScreeningDAO;
import com.solvd.airport.models.Screening;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ScreeningDAO extends AbstractMySQLDAO<Screening> implements IScreeningDAO<Screening> {
    private static final Logger logger = LogManager.getLogger(ScreeningDAO.class);
    private static final String GET_SCREENING_BY_ID = "SELECT * FROM screenings WHERE id=?";
    private static final String GET_ALL_SCREENINGS = "SELECT * FROM screenings";
    private static final String INSERT_SCREENING = "INSERT INTO screenings (terminal_id, baggage_id, " +
            "result) VALUES (?,?,?)";
    private static final String UPDATE_SCREENING_BY_ID = "UPDATE screenings SET terminal_id=?, baggage_id=?, " +
            "result=? WHERE id=?";
    private static final String DELETE_SCREENING_BY_ID = "DELETE FROM screenings WHERE id=?";

    @Override
    public void createEntity(Screening screening) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(INSERT_SCREENING);
                st.setLong(1, screening.getTerminalId());
                st.setLong(2, screening.getBaggageId());
                st.setString(3, screening.getResult());
            } catch (Exception e) {
                logger.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public void updateEntity(Screening screening) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(UPDATE_SCREENING_BY_ID);
                st.setLong(1, screening.getTerminalId());
                st.setLong(2, screening.getBaggageId());
                st.setString(3, screening.getResult());
                st.setLong(4, screening.getId());
            } catch (Exception e) {
                logger.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public Screening getEntityById(long id){
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_SCREENING_BY_ID);
                st.setLong(1, id);
            } catch (SQLException e) {
                logger.error(e);
            }
        };

        return getEntitiesHelper(runnable).get(0);
    }

    @Override
    public void deleteEntityById(long id) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(DELETE_SCREENING_BY_ID);
                st.setLong(1, id);
            } catch (Exception e) {
                logger.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public List<Screening> getAllEntities(){
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_ALL_SCREENINGS);
            } catch (SQLException e) {
                logger.error(e);
            }
        };

        return getEntitiesHelper(runnable);
    }

    @Override
    protected Screening resultSetToEntity(ResultSet rs) {
        Screening screening = new Screening();

        try {
            screening.setId(rs.getLong("id"));
            screening.setResult(rs.getString("result"));
            screening.setTerminalId(rs.getLong("terminal_id"));
            screening.setBaggageId(rs.getLong("baggage_id"));
        } catch (Exception e) {
            logger.error(e);
        }

        return screening;
    }
}
