package org.but.feec.airport.api;



import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class Passenger {
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty surname = new SimpleStringProperty();
    private SimpleStringProperty email = new SimpleStringProperty();
    private SimpleStringProperty password = new SimpleStringProperty();
    private SimpleStringProperty phoneNumber = new SimpleStringProperty();
    private SimpleLongProperty contactId = new SimpleLongProperty();

    public String getName() {
        return name.get();
    }
    public String getSurname() {
        return surname.get();
    }
    public String getEmail() {
        return email.get();
    }
    public String getPassword() {
        return password.get();
    }
    public String getPhoneNumber() {
        return phoneNumber.get();
    }
    public long getContactId() {
        return contactId.get();
    }
    
    public void setName(String name) {
        this.name.setValue(name);
    }
    public void setSurname(String surname) {
        this.surname.setValue(surname);
    }
    public void setEmail(String email) {
        this.email.setValue(email);
    }
    public void setPassword(String password) {
        this.password.setValue(password);
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.setValue(phoneNumber);
    }
    public void setContactId(long contactId) {
        this.contactId.setValue(contactId);
    }
    // TODO: implement toString()
}
