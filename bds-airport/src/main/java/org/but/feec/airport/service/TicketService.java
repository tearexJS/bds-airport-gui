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
    public List<TicketView> searchForTickets(String destination){
        return ticketRepository.availableTickets(destination);
    }
    public List<String> loadFlightClasses(){
        return ticketRepository.loadFlightClasses();
    }
    public Boolean bookTicket(TicketView ticket, String flightClass, String email){
        return ticketRepository.bookTicket(ticket, flightClass, email);
    }
    public Boolean deleteTicket(TicketView ticket, String email){
       return ticketRepository.deleteTicket(ticket, email);
    }
}
