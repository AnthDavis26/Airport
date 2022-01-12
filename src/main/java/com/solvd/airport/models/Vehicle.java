package com.solvd.airport.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="vehicle")
@XmlType(propOrder = {"id","user-id","airline"})
public class Vehicle {
    private Long id;
    private Long userId;
    private Long airlineId;
    private Long passengerCapacity;

    public Vehicle() {
        super();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @XmlAttribute(name="id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(name="user-id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @XmlElement(name="airline-id")
    public Long getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(Long airlineId) {
        this.airlineId = airlineId;
    }

    @XmlElement(name="passenger-capacity")
    public Long getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(Long passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }
}
