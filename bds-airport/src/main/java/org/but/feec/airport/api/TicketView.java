package org.but.feec.airport.api;

import javafx.beans.property.*;

public class TicketView {
    private StringProperty name = new SimpleStringProperty();
    private StringProperty surname = new SimpleStringProperty();
    private IntegerProperty idFlight = new SimpleIntegerProperty();
    private StringProperty airline = new SimpleStringProperty();
    private StringProperty gate = new SimpleStringProperty();
    private StringProperty flightClass = new SimpleStringProperty();
    private DoubleProperty cost = new SimpleDoubleProperty();

    public String getName() {
        return name.get();
    }
    public String getSurname() {
        return surname.get();
    }
    public int getIdFlight() {
        return idFlight.get();
    }
    public String getAirline() {
        return airline.get();
    }
    public String getGate() {
        return gate.get();
    }
    public String getFlightClass() {
        return flightClass.get();
    }
    public double getCost() {
        return cost.get();
    }

    public void setName(String name) {
        this.name.setValue(name);
    }
    public void setSurname(String surname) {
        this.surname.setValue(surname);
    }
    public void setIdFlight(int idFlight) {
        this.idFlight.setValue(idFlight);
    }
    public void setAirline(String airline) {
        this.airline.setValue(airline);
    }
    public void setGate(String gate) {
        this.gate.setValue(gate);
    }
    public void setFlightClass(String flightClass) {
        this.flightClass.setValue(flightClass);
    }
    public void setCost(double cost) {
        this.cost.setValue(cost);
    }

    @Override
    public String toString() {
        return "Ticket{" + 
        "name="+this.name+
        "surname="+this.surname+
        "id_flight"+ idFlight+
        "airline="+ this.airline+
        "gate="+this.gate+
        "class="+this.flightClass+
        "cost="+cost+"}";
    }
}
