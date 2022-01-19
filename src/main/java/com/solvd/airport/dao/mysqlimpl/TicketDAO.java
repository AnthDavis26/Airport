package com.solvd.airport.dao.mysqlimpl;

import com.solvd.airport.dao.ITicketDAO;
import com.solvd.airport.models.Ticket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TicketDAO extends AbstractMySQLDAO<Ticket> implements ITicketDAO<Ticket> {
    private static final Logger LOGGER = LogManager.getLogger(TicketDAO.class);
    private static final String INSERT_TICKET = "INSERT INTO tickets (user_id, transaction_id, " +
            "flight_id, seat_id) VALUES (?,?,?)";
    private static final String GET_TICKET_BY_ID = "SELECT * FROM tickets WHERE id=?";
    private static final String GET_ALL_TICKETS = "SELECT * FROM tickets";
    private static final String UPDATE_TICKET_BY_ID = "UPDATE tickets SET user_id=?, transaction_id=?, " +
            "flight_id=?, seat_id=? WHERE id=?";
    private static final String DELETE_TICKET_BY_ID = "DELETE FROM tickets WHERE id=?";

    @Override
    public void createEntity(Ticket ticket) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(INSERT_TICKET);
                st.setLong(1, ticket.getTransactionId());
                st.setLong(2, ticket.getUserId());
                st.setLong(3, ticket.getFlightId());
                st.setLong(4, ticket.getSeatId());
            } catch (Exception e) {
                LOGGER.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public void updateEntity(Ticket ticket) {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(UPDATE_TICKET_BY_ID);
                st.setLong(1, ticket.getTransactionId());
                st.setLong(2, ticket.getUserId());
                st.setLong(3, ticket.getFlightId());
                st.setLong(4, ticket.getSeatId());
                st.setLong(5, ticket.getId());
            } catch (Exception e) {
                LOGGER.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public Ticket getEntityById(long id){
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_TICKET_BY_ID);
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
                st = con.prepareStatement(DELETE_TICKET_BY_ID);
                st.setLong(1, id);
            } catch (Exception e) {
                LOGGER.error(e);
            }
        };

        updateEntitiesHelper(runnable);
    }

    @Override
    public List<Ticket> getAllEntities() {
        Runnable runnable = () -> {
            try {
                st = con.prepareStatement(GET_ALL_TICKETS);
            } catch (Exception e) {
                LOGGER.error(e);
            }
        };

        return getEntitiesHelper(runnable);
    }

    @Override
    protected Ticket resultSetToEntity(ResultSet rs) {
        Ticket ticket = new Ticket();

        try {
            ticket.setId(rs.getLong("id"));
            ticket.setFlightId(rs.getLong("flight_id"));
            ticket.setSeatId(rs.getLong("seat_id"));
            ticket.setTransactionId(rs.getLong("transaction_id"));
            ticket.setUserId(rs.getLong("user_id"));
        } catch (Exception e) {
            LOGGER.error(e);
        }

        return ticket;
    }
}
