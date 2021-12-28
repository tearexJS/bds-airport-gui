package org.but.feec.airport.service;

import java.util.List;

import org.but.feec.airport.api.Passenger;
import org.but.feec.airport.data.AdminRepository;

public class AdminService {
    private AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }

    public List<Passenger> showAllPassengers(){
        return adminRepository.showAllPassengers();
    }

}
