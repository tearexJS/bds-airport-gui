package org.but.feec.airport.data;

import org.but.feec.airport.api.*;
import org.but.feec.airport.config.DataSourceConfig;
//import org.but.feec.airport.exceptions.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {
    
    public EmployeeAuthView findEmployeeByEmail(String email){
        try(Connection conn = DataSourceConfig.getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT email, password FROM airport.employee WHERE email=?")
        ){
            statement.setString(1, email);
            try(ResultSet rs = statement.executeQuery()){
                if(rs.next())
                    return mapToEmployeeAuthView(rs);

            }
        }catch(SQLException e){
            e.printStackTrace();
            //TODO: create custom exceptions
        }
        return null;
    }

    private EmployeeAuthView mapToEmployeeAuthView(ResultSet rs) throws SQLException{
        EmployeeAuthView employee = new EmployeeAuthView();
        employee.setEmail(rs.getString("email"));
        employee.setPassword(rs.getString("password"));
        return employee;
    }

}

