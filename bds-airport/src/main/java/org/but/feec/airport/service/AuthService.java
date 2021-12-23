package org.but.feec.airport.service;

//import org.but.feec.airport.data.PersonRepository;

public class AuthService {
    private PersonRepository personRepository;
    
    public AuthService(PersonRepository personRepository ){
        this.personRepository=personRepository;
    }
}
