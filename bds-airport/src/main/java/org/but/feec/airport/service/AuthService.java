package org.but.feec.airport.service;

//import org.but.feec.airport.data.PersonRepository;

public class AuthService {
    private EmployeeRepository EmployeeRepository;
    
    public AuthService(EmployeeRepository employeeRepository ){
        this.employeeRepository=employeeRepository;
    }
}
