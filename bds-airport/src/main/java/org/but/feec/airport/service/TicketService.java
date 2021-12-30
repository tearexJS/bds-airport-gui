package org.but.feec.airport.service;

import org.but.feec.airport.data.TicketRepository;

import java.util.List;

import org.but.feec.airport.api.TicketView;

public class TicketService {
    private TicketRepository ticketRepository;
    
    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository=ticketRepository;
    }
    public List<TicketView> showPassengersTickets(String email){
        return ticketRepository.showPassengersTickets(email);
    }
}
