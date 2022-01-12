package com.solvd.airport.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tickets")
public class Tickets {
    private List<Gate> tickets;

    @XmlElement(name = "gate")
    public List<Gate> getTickets() {
        return tickets;
    }

    public void setTickets(List<Gate> tickets) {
        this.tickets = tickets;
    }
}