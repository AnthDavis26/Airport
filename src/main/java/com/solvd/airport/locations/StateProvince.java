package com.solvd.airport.locations;

import com.solvd.airport.enums.StateName;

public class StateProvince extends Location{
    private Country country;
    private String acronym;

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

    public StateProvince(StateName name, Country country) {
        super.setName(name.getName());
        this.acronym = name.getAcronym();
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }
}
