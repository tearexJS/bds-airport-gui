package org.but.feec.airport.api;

import javafx.beans.property.*;

public class FlightInfoView {
    private StringProperty name = new SimpleStringProperty();
    private StringProperty surname = new SimpleStringProperty();
    private StringProperty gate = new SimpleStringProperty();
    private StringProperty departureTime = new SimpleStringProperty();

    public String getName() {
        return name.get();
    }
    public String getSurname() {
        return surname.get();
    }
    public String getGate() {
        return gate.get();
    }
    public String getDepartureTime() {
        return departureTime.get();
    }

    public void setName(String name) {
        this.name.setValue(name);
    }
    public void setSurname(String surname) {
        this.surname.setValue(surname);
    }
    public void setGate(String gate) {
        this.gate.setValue(gate);
    }
    public void setDepartureTime(String departureTime) {
        this.departureTime.setValue(departureTime);
    }

    @Override
    public String toString() {
        return "FlightInfo{"+
        "name="+this.name+
        "surname="+this.surname+
        "gate="+this.gate+
        "departureTime="+this.departureTime+"}";
    }
}
