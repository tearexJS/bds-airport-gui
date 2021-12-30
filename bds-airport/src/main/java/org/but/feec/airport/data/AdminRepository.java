package org.but.feec.airport.data;

import org.but.feec.airport.api.Passenger;
import org.but.feec.airport.config.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AdminRepository {
    private static final Logger logger = LoggerFactory.getLogger(AdminRepository.class);
    public List<Passenger> showAllPassengers(){

        try(Connection conn = DataSourceConfig.getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT p.first_name, p.surname, p.id_contact, c.password, c.phone_number, c.email FROM airport.passenger p JOIN airport.contact c ON c.id_contact = p.id_contact")
        ){
            return mapToAdminsViewList(statement);
        }catch(SQLException e){
            logger.error("Query to retrieve all passengers failed"+e.getMessage());
        }
        return null;
    }

    public List<Passenger> findPassengerBySurname(String surname){
        try(Connection conn = DataSourceConfig.getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT p.first_name, p.surname, p.id_contact, c.password, c.phone_number, c.email FROM airport.passenger p JOIN airport.contact c ON c.id_contact = p.id_contact WHERE p.surname=?");
        ){
            statement.setString(1, surname);
            List<Passenger> result = mapToAdminsViewList(statement);
            return result;
        }catch(SQLException e){
            logger.error("Query to find passenger by surname failed"+ e.getMessage());
        }
        return null;
    }


    private Passenger mapToAdminsView(ResultSet rs){
        Passenger adminView = new Passenger();
        try{
            adminView.setName(rs.getString("first_name"));
            adminView.setSurname(rs.getString("surname"));
            adminView.setEmail(rs.getString("email"));
            adminView.setPhoneNumber(rs.getString("phone_number"));
            adminView.setPassword(rs.getString("password"));
            adminView.setContactId(rs.getLong("id_contact"));
            return adminView;
        }catch(SQLException e){
            logger.error("Mapping to admin view raised an exception" + e.getMessage());
        }
        return null;
    }
    private List<Passenger> mapToAdminsViewList(PreparedStatement statement){
        List<Passenger> allPassengers = new ArrayList<>();
        try(ResultSet rs = statement.executeQuery()){
            while(rs.next()){

                Passenger adminView = mapToAdminsView(rs);
                allPassengers.add(adminView);
            }
            return allPassengers;
        }catch(SQLException e){
            logger.error("Query to retrieve all passengers failed"+e.getMessage());
        }
        return null;
    }

}
