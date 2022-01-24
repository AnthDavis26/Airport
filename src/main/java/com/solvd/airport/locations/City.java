package com.solvd.airport.locations;

public class City extends Location {
    private StateProvince stateProvince;

    private City(){}

    public City(String name, StateProvince stateProvince) {
        super.setName(name);
        this.stateProvince = stateProvince;
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
        return super.getName();
    }

    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }

    public StateProvince getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(StateProvince stateProvince) {
        this.stateProvince = stateProvince;
    }
}
