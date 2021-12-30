package org.but.feec.airport.service;

import org.but.feec.airport.api.AuthView;
import org.but.feec.airport.data.*;
import at.favre.lib.crypto.bcrypt.BCrypt;

public class AuthService {
    private LoginRepository loginRepository;
    
    public AuthService(LoginRepository loginRepository ){
        this.loginRepository=loginRepository;
    }

    public int authenticate(String email, String password){
        if(email == null || password == null || email.isEmpty() || password.isEmpty()){
            return 0;
        }
        AuthView employee = loginRepository.findPersonByEmail(email);
        if(employee == null){
            return 0;
        }
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), employee.getPassword());
        if(!result.verified){
            return 0;
        }

        EmailHolderService.getInstance().setEmail(email);
        
        switch(employee.getTab()){
            case "passenger":
                return 1;
            case "Admin":
                return 2;
            default:
                return 0;
        }
        
    }
}
