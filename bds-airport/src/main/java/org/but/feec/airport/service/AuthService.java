package org.but.feec.airport.service;

import org.but.feec.airport.api.EmployeeAuthView;
import org.but.feec.airport.data.*;
import at.favre.lib.crypto.bcrypt.BCrypt;
public class AuthService {
    private EmployeeRepository employeeRepository;
    
    public AuthService(EmployeeRepository employeeRepository ){
        this.employeeRepository=employeeRepository;
    }

    public boolean authenticate(String email, String password){
        if(email == null || password == null || email.isEmpty() || password.isEmpty()){
            return false;
        }
        EmployeeAuthView employee = employeeRepository.findEmployeeByEmail(email);
        if(employee == null){

        }
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), employee.getPassword());
        return result.verified;
    }
}
