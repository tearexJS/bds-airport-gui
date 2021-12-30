package org.but.feec.airport.api;

import javafx.beans.property.SimpleStringProperty;

public class InjectionView {
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty surname = new SimpleStringProperty();

    public String getName() {
        return name.get();
    }
    public String getSurname() {
        return surname.get();
    }

    public void setName(String name) {
        this.name.setValue(name);
    }
    public void setSurname(String surname) {
        this.surname.setValue(surname);
    }
}
