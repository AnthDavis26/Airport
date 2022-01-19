package com.solvd.airport.dao.mysqlimpl;

import com.solvd.airport.dao.IScheduleDAO;
import com.solvd.airport.models.Schedule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class ScheduleDAO extends AbstractMySQLDAO<Schedule> implements IScheduleDAO<Schedule> {
    private static final Logger LOGGER = LogManager.getLogger(ScheduleDAO.class);
    private static final String INSERT_SCHEDULE = "INSERT INTO schedules (arrival, departure) VALUES (?,?)";;
    private static final String UPDATE_SCHEDULE_BY_ID = "UPDATE schedules SET arrival=?, departure=? WHERE id=?";
    private static final String GET_SCHEDULE_BY_ID = "SELECT * FROM schedules WHERE id=?";
    private static final String GET_ALL_SCHEDULES = "SELECT * FROM schedules";
    private static final String DELETE_SCHEDULE_BY_ID = "DELETE FROM schedules WHERE id=?";

    @Override
    public void createEntity(Schedule schedule) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(INSERT_SCHEDULE);
                st.setTimestamp(1, Timestamp.valueOf(schedule.getArrival()));
                st.setTimestamp(2, Timestamp.valueOf(schedule.getDeparture()));
            } catch (Exception e) {
                LOGGER.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public void updateEntity(Schedule schedule) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(UPDATE_SCHEDULE_BY_ID);
                st.setTimestamp(1, Timestamp.valueOf(schedule.getArrival()));
                st.setTimestamp(2, Timestamp.valueOf(schedule.getDeparture()));
                st.setLong(3, schedule.getId());
            } catch (Exception e) {
                LOGGER.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public Schedule getEntityById(long id) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_SCHEDULE_BY_ID);
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
                st = con.prepareStatement(DELETE_SCHEDULE_BY_ID);
                st.setLong(1, id);
            } catch (Exception e) {
                LOGGER.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public List<Schedule> getAllEntities(){
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_ALL_SCHEDULES);
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        };

        return getEntitiesHelper(runnable);
    }

    @Override
    protected Schedule resultSetToEntity(ResultSet rs) {
        Schedule schedule = new Schedule();

        try {
            schedule.setId(rs.getLong("id"));
            schedule.setArrival(rs.getTimestamp("arrival").toLocalDateTime());
            schedule.setDeparture(rs.getTimestamp("departure").toLocalDateTime());
        } catch (Exception e) {
            LOGGER.error(e);
        }

        return schedule;
    }
}
