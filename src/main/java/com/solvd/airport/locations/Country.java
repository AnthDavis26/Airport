package com.solvd.airport.locations;

public class Country extends Location {
	public Country(String name) {
        super.setName(name);
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
}
