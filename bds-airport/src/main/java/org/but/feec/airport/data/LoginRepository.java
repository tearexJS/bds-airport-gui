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
            PreparedStatement statement = conn.prepareStatement("SELECT email, password, role FROM(SELECT email, password, position AS role FROM airport.employee JOIN airport.employee_type ON employee.id_employee_type = employee_type.id_employee_type UNION SELECT email, password, 'passenger' AS role FROM airport.contact) AS person WHERE email = ?")
        ){
            statement.setString(1, email);
            try(ResultSet rs = statement.executeQuery()){
                if(rs.next())
                    return mapToAuthView(rs);

            }
        }catch(SQLException e){
            e.printStackTrace();
            //TODO: implement logger
        }
        return null;
    }

    private AuthView mapToAuthView(ResultSet rs) throws SQLException{
        AuthView person = new AuthView();
        person.setEmail(rs.getString("email"));
        person.setPassword(rs.getString("password"));
        person.setTab(rs.getString("role"));
        return person;
    }

}

