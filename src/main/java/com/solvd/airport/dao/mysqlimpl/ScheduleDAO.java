package com.solvd.airport.dao.mysqlimpl;

import com.solvd.airport.dao.IScheduleDAO;
import com.solvd.airport.models.Schedule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ScheduleDAO extends AbstractMySQLDAO<Schedule> implements IScheduleDAO<Schedule> {
    @Override
    public void createEntity(Schedule entity) {

    }

    @Override
    public void updateEntity(Schedule entity) {

    }

    @Override
    public Schedule getEntityById(long id) {
        return null;
    }

    @Override
    public void deleteEntityById(long id) {

    }

    @Override
    public List<Schedule> getAllEntities() {
        return null;
    }

    @Override
    protected Schedule resultSetToEntity(ResultSet rs) {
        return null;
    }
}
