package org.but.feec.airport.service;

import java.util.List;

import org.but.feec.airport.api.InjectionView;
import org.but.feec.airport.data.InjectionRepository;

public class InjectionService {
    private InjectionRepository injectionRepository;

    public InjectionService(InjectionRepository injectionRepository){
        this.injectionRepository = injectionRepository;
    }
    public List<InjectionView> showAll(){
        return injectionRepository.showAll();
    }
    public Boolean addElement(String name, String surname){
        return injectionRepository.addElement(name, surname);
    }
    public List<InjectionView> findElement(String surname){
        return injectionRepository.searchForElement(surname);
    }
}
