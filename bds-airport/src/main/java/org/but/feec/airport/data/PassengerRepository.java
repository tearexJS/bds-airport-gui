package org.but.feec.airport.data;

import org.but.feec.airport.*;

import org.but.feec.airport.api.Passenger;
import org.but.feec.airport.config.*;
import java.sql.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.favre.lib.crypto.bcrypt.BCrypt;
public class PassengerRepository {
    private static final Logger logger = LoggerFactory.getLogger(PassengerRepository.class);

    public Boolean addPassenger(Passenger adminView){
        String hashedPassword = hashPassword(adminView.getPassword().toCharArray());
        try(Connection conn = DataSourceConfig.getConnection()){
            try(PreparedStatement insertContact = conn.prepareStatement("INSERT INTO airport.contact (email, phone_number, password) VALUES(?, ?, ?)");
                PreparedStatement insertPassenger = conn.prepareStatement("INSERT INTO airport.passenger (first_name, surname, id_contact) VALUES (?, ?, (SELECT id_contact FROM airport.contact WHERE email=?))");
            ){
                conn.setAutoCommit(false);
                insertContact.setString(1, adminView.getEmail());
                insertContact.setString(2, adminView.getPhoneNumber());
                insertContact.setString(3, hashedPassword);
                insertContact.executeUpdate();

                insertPassenger.setString(1, adminView.getName());
                insertPassenger.setString(2, adminView.getSurname());
                insertPassenger.setString(3, adminView.getEmail());
                insertPassenger.executeUpdate();
                conn.commit();
                return true;
            }catch(SQLException e){
                conn.rollback();
            }
        }catch(SQLException e){
            logger.error("Something went wrong while connecting to the database"+e.getMessage());
        }
        return false;
    }
    public Boolean changePassengerEmail(String oldEmail, String newEmail){
        try(Connection conn = DataSourceConfig.getConnection();
            PreparedStatement changePassword = conn.prepareStatement("UPDATE airport.contact SET email=? WHERE email=?")
        ){
            changePassword.setString(1, newEmail);
            changePassword.setString(2, oldEmail);
            changePassword.execute();
            return true;
        }catch(SQLException e){
            logger.error("Failed to execute change email query"+e.getMessage());
        }
        return false;
    }
    public Boolean deletePassenger(String email){
        try(Connection conn = DataSourceConfig.getConnection();
            PreparedStatement deleteStatement = conn.prepareStatement("DELETE FROM airport.contact WHERE email=?")
        ){
            deleteStatement.setString(1, email);
            deleteStatement.execute();
            return true;
        }catch(SQLException e){
            logger.error("Failed to execute delete query" + e.getMessage());
        }
        return false;
    }
    private String hashPassword(char[] plainPassword){
        String hashedPassword = BCrypt.withDefaults().hashToString(12, plainPassword);
        return hashedPassword;
     }

}
