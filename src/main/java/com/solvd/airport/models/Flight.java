package com.solvd.airport.models;

import com.solvd.airport.enums.FlightStatus;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="flight")
@XmlType(propOrder = {"id","schedule-id","status","gate-id","vehicle-id"})
public class Flight {
    private Long id;
    private Long scheduleId;
    private String status;
    private Long gateId;
    private Long vehicleId;

    public Flight() {
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

    @XmlElement(name="schedule-id")
    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    @XmlElement(name="status")
    public String getStatus() {
        return status;
    }

    public void setStatus(FlightStatus flightStatus) {
        this.status = flightStatus.getMessage();
    }

    @XmlElement(name="gate-id")
    public Long getGateId() {
        return gateId;
    }

    public void setGateId(Long gateId) {
        this.gateId = gateId;
    }

    @XmlElement(name="vehicle-id")
    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }
}
