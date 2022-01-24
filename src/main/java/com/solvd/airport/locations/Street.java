package com.solvd.airport.locations;

public class Street {
    private City city;

    public Street(City city) {
        this.city = city;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
