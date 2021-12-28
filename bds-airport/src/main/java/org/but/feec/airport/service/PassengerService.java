package org.but.feec.airport.service;

import org.but.feec.airport.api.Passenger;
import org.but.feec.airport.data.PassengerRepository;

public class PassengerService {
    private PassengerRepository passengerRepository;

    public PassengerService(PassengerRepository passengerRepository){
        this.passengerRepository = passengerRepository;
    }
    public Boolean createPassenger(String name, String surname, String email, String phoneNumber, String password){
        Passenger newPassenger = new Passenger();
        newPassenger.setName(name);
        newPassenger.setSurname(surname);
        newPassenger.setEmail(email);
        newPassenger.setPhoneNumber(phoneNumber);
        newPassenger.setPassword(password);
        
        return passengerRepository.addPassenger(newPassenger);
    }
    public Boolean updatePassengerEmail(String oldEmail, String newEmail){
        return passengerRepository.changePassengerEmail(oldEmail, newEmail);
    }
    public Boolean deletePassenger(String email){
        return passengerRepository.deletePassenger(email);
    }
}
