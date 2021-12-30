package com.solvd.airport.models;

public class Screening {
    private Long id;
    private Long terminalId;
    private Long baggageId;
    private String result;

    public Screening() {
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

    public Long getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(Long terminalId) {
        this.terminalId = terminalId;
    }

    public Long getBaggageId() {
        return baggageId;
    }

    public void setBaggageId(Long baggageId) {
        this.baggageId = baggageId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
