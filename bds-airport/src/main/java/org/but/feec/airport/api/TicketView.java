package org.but.feec.airport.api;

import javafx.beans.property.*;

public class TicketView {
    private StringProperty destination = new SimpleStringProperty();
    private IntegerProperty idFlight = new SimpleIntegerProperty();
    private StringProperty airline = new SimpleStringProperty();
    private StringProperty gate = new SimpleStringProperty();
    private StringProperty flightClass = new SimpleStringProperty();
    private DoubleProperty cost = new SimpleDoubleProperty();

    public String getDestination() {
        return destination.get();
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

    public void setDestination(String destination) {
        this.destination.setValue(destination);
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
        "name="+this.destination+
        "id_flight"+ idFlight+
        "airline="+ this.airline+
        "gate="+this.gate+
        "class="+this.flightClass+
        "cost="+cost+"}";
    }
}
