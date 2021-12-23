package org.but.feec.airport.service;

import org.but.feec.airport.data.*;

public class AuthService {
    private EmployeeRepository employeeRepository;
    
    public AuthService(EmployeeRepository employeeRepository ){
        this.employeeRepository=employeeRepository;
    }
}
