package com.solvd.airport.enums;

public enum FlightStatus {
    ON_TIME("on time"), DELAYED("delayed");

    private final String message;

    FlightStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
