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
    private static final Logger logger = LoggerFactory.getLogger(TicketRepository.class);
    public List<TicketView> showPassengersTickets(String email){
        try(Connection conn = DataSourceConfig.getConnection();
            PreparedStatement selectTickets = conn.prepareStatement("SELECT ticket.id_flight, dst.departure_time, dst.airline_name, dst.gate, ticket_type.class, ticket_type.cost, dst.destination_name "+
                                                                    "FROM airport.ticket "+
                                                                    "JOIN (SELECT flight.id_flight, flight.gate, flight.departure_time,airline.airline_name, destination.destination_name FROM airport.flight JOIN airport.destination ON flight.id_destination = destination.id_destination JOIN airport.airline ON flight.id_airline = airline.id_airline) AS dst ON dst.id_flight = ticket.id_flight "+
                                                                    "JOIN airport.ticket_type ON ticket_type.id_ticket_type = ticket.id_ticket_type "+
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
    public List<TicketView> availableTickets(String destination){
        try(Connection conn = DataSourceConfig.getConnection();
            PreparedStatement searchForAvailableTickets = conn.prepareStatement("SELECT flight.id_flight, airline.airline_name, flight.gate, flight.departure_time, destination.destination_name "+
                                                                                "FROM airport.flight "+
                                                                                "JOIN airport.destination ON flight.id_destination = destination.id_destination "+
                                                                                "JOIN airport.airline ON flight.id_airline = airline.id_airline "+
                                                                                "WHERE destination.destination_name = ?;")
        )
        {
            searchForAvailableTickets.setString(1, destination);
            ResultSet rs = searchForAvailableTickets.executeQuery();
            return mapTicketBookingToTicketview(rs);
        }catch(SQLException e){
            logger.error("Failed to retrieve available tickets for passenger "+e.getMessage());
        }
        return null;
    }

    public Boolean bookTicket(TicketView ticket, String flightClass, String email){
        try(Connection conn = DataSourceConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO airport.ticket(id_flight, id_passenger, id_ticket_type) VALUES(?,(SELECT id_passenger FROM airport.passenger JOIN airport.contact ON passenger.id_contact = contact.id_contact WHERE contact.email=?), (SELECT id_ticket_type FROM airport.ticket_type WHERE class=?))")    
        ){
            ps.setInt(1, ticket.getIdFlight());
            ps.setString(2, email);
            ps.setString(3, flightClass);
            ps.execute();
            return true;
        }catch(SQLException e){
            logger.error("Failed to book ticket "+e.getMessage());
        }
        return false;
    }
    public Boolean deleteTicket(TicketView ticket, String email){
        try(Connection conn = DataSourceConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM airport.ticket WHERE id_flight = ? AND id_passenger = (SELECT id_passenger FROM airport.passenger JOIN airport.contact ON passenger.id_contact = contact.id_contact WHERE contact.email=?);");
        ){
            ps.setInt(1, ticket.getIdFlight());
            ps.setString(2, email);
            ps.execute();
            return true;
        }
        catch(SQLException e){
            logger.error("Failed to delete ticket"+e.getMessage());
        }
        return false;
    }

    public List<String> loadFlightClasses(){
        try(Connection conn = DataSourceConfig.getConnection();
            PreparedStatement classes = conn.prepareStatement("SELECT class FROM airport.ticket_type")
        ){
            ResultSet rs = classes.executeQuery();
            List<String> flightClasses = new ArrayList<>();
            while(rs.next()){
                flightClasses.add(rs.getString("class"));
            }
            return flightClasses;

        }catch(SQLException e){
            logger.error("Failed to load flight classes "+ e.getMessage());
        }
        return null;
    }
    private List<TicketView> mapTicketBookingToTicketview(ResultSet rs){
        List<TicketView> tickets = new ArrayList<>();
        try{
            while(rs.next()){
                TicketView ticket = new TicketView();
                ticket.setAirline(rs.getString("airline_name"));
                ticket.setDestination(rs.getString("destination_name"));
                ticket.setIdFlight(rs.getInt("id_flight"));
                ticket.setDepartureTime(rs.getTimestamp("departure_time").toString());
                ticket.setGate(rs.getString("gate"));
                tickets.add(ticket);
            }
            return tickets;
        }
        catch(SQLException e){
            logger.error("");
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
                ticket.setGate(rs.getString("gate"));
                ticket.setDepartureTime(rs.getTimestamp("departure_time").toString());
                tickets.add(ticket);
            }
            return tickets;
        }
        catch(SQLException e){
            logger.error("");
        }
        return null;
    }
}
