package com.solvd.airport.models;

public class Airport {
    private Long id;
    private String name;
    private String iata;

    public Airport() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIATA() {
        return iata;
    }

    public void setIATA(String iata) {
        this.iata = iata;
    }
}
