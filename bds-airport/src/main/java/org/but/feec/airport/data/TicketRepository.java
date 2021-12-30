package org.but.feec.airport.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.but.feec.airport.api.TicketView;
import org.but.feec.airport.config.DataSourceConfig;

public class TicketRepository {
    private static final Logger logger = LoggerFactory.getLogger(PassengerRepository.class);
    public List<TicketView> showPassengersTickets(String email){
        try(Connection conn = DataSourceConfig.getConnection();
            PreparedStatement selectTickets = conn.prepareStatement("SELECT ticket.id_flight, airline.airline_name, flight.gate, ticket_type.class, ticket_type.cost, destination.destination_name"+
                                                                    "FROM airport.ticket"+
                                                                    "JOIN airport.passenger ON ticket.id_passenger = passenger.id_passenger"+
                                                                    "JOIN airport.airline ON airline.id_airline = ticket.id_airline"+
                                                                    "JOIN airport.ticket_type ON ticket_type.id_ticket_type = ticket.id_ticket_type"+
                                                                    "JOIN airport.flight ON flight.id_flight = ticket.id_flight"+
                                                                    "JOIN airport.destination ON destination.id_destination = ticket.id_destination"+
                                                                    "WHERE ticket.id_passenger = (SELECT id_passenger FROM airport.passenger JOIN airport.contact ON passenger.id_contact = contact.id_contact WHERE contact.email=?);")
        ){
            selectTickets.setString(1, email);
            ResultSet rs = selectTickets.executeQuery();
            return mapToTicketView(rs);
        }catch(SQLException e){
            logger.error("Failed to retrieve passengers tickets "+e.getMessage());
        }
        return null;
    }
    private List<TicketView> mapToTicketView(ResultSet rs){
        List<TicketView> tickets = new ArrayList<>();
        try{
            while(rs.next()){
                TicketView ticket = new TicketView();
                ticket.setAirline(rs.getString("airline_name"));
                ticket.setDestination(rs.getString("destination_name"));
                ticket.setFlightClass(rs.getString("class"));
                ticket.setIdFlight(rs.getInt("id_flight"));
                ticket.setCost(rs.getDouble("cost"));
            }
            return tickets;
        }
        catch(SQLException e){
            logger.error("");
        }
        return null;
    }
}
