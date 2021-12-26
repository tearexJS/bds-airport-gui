package org.but.feec.airport.data;

import org.but.feec.airport.api.*;
import org.but.feec.airport.config.DataSourceConfig;
//import org.but.feec.airport.exceptions.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginRepository {
    
    public AuthView findPersonByEmail(String email){
        try(Connection conn = DataSourceConfig.getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT email, password, tab FROM(SELECT email, password, 'employee' as tab FROM airport.employee UNION SELECT email, password, 'passenger' as tab FROM airport.contact) AS person WHERE email = ?")
        ){
            statement.setString(1, email);
            try(ResultSet rs = statement.executeQuery()){
                if(rs.next())
                    return mapToAuthView(rs);

            }
        }catch(SQLException e){
            e.printStackTrace();
            //TODO: create custom exceptions
        }
        return null;
    }

    private AuthView mapToAuthView(ResultSet rs) throws SQLException{
        AuthView person = new AuthView();
        person.setEmail(rs.getString("email"));
        person.setPassword(rs.getString("password"));
        person.setTab(rs.getString("tab"));
        return person;
    }

}

