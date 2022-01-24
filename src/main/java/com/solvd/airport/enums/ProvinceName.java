package com.solvd.airport.enums;

public enum ProvinceName {
    ALBERTA("Alberta", "AB"), BRITISH_COLUMBIA("British Columbia", "BC"), MANITOBA("Manitoba", "MB"),
    NEW_BRUNSWICK("New Brunswick", "NB"), NEWFOUNDLAND_AND_LABRADOR("Newfoundland and Labrador", "NL"),
    NORTHWEST_TERRITORIES("Northwest Territories", "NT"), NOVA_SCOTIA("Nova Scotia", "NS"), NUNAVUT("Nunavut", "NU"),
    ONTARIO("Ontario", "ON"), PRINCE_EDWARD_ISLAND("Prince Edward Island", "PE"), QUEBEC("Quebec", "QC"),
    SASKATCHEWAN("Saskatchewan", "SK"), YUKON("Yukon", "YT");

    private String name;
    private String acronym;

    ProvinceName(String name, String acronym) {
        this.name = name;
        this.acronym = acronym;
    }

    public String getName() {
        return name;
    }

    public String getAcronym() {
        return acronym;
    }

    @Override
    public String toString() {
        return name + " (" + acronym + ")";
    }
}
