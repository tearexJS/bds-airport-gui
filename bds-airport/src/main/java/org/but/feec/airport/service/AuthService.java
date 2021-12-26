package org.but.feec.airport.service;

import org.but.feec.airport.api.AuthView;
import org.but.feec.airport.data.*;
import at.favre.lib.crypto.bcrypt.BCrypt;
public class AuthService {
    private LoginRepository loginRepository;
    
    public AuthService(LoginRepository loginRepository ){
        this.loginRepository=loginRepository;
    }

    public boolean authenticate(String email, String password){
        if(email == null || password == null || email.isEmpty() || password.isEmpty()){
            return false;
        }
        AuthView employee = loginRepository.findPersonByEmail(email);
        if(employee == null){
            return false;
        }
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), employee.getPassword());
        return result.verified;
    }
}
