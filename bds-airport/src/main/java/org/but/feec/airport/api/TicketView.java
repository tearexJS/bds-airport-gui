package org.but.feec.airport.api;

public class TicketView {
    private String name;
    private String surname;
    private int idFlight;
    private String airline;
    private String gate;
    private String flightClass;
    private double cost;

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public int getIdFlight() {
        return idFlight;
    }
    public String getAirline() {
        return airline;
    }
    public String getGate() {
        return gate;
    }
    public String getFlightClass() {
        return flightClass;
    }
    public double getCost() {
        return cost;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setIdFlight(int idFlight) {
        this.idFlight = idFlight;
    }
    public void setAirline(String airline) {
        this.airline = airline;
    }
    public void setGate(String gate) {
        this.gate = gate;
    }
    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
}
