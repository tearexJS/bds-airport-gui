package org.but.feec.airport.data;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.but.feec.airport.api.InjectionView;
import org.but.feec.airport.config.DataSourceConfig;

public class InjectionRepository {
    private static final Logger logger = LoggerFactory.getLogger(InjectionRepository.class);
    public Boolean addElement(String name, String surname){
        try(Connection conn = DataSourceConfig.getConnection();
            Statement statement = conn.createStatement()
        ){
            String query = String.format("INSERT INTO airport.injection_training (first_name, surname) VALUES ('%s', '%s')", name, surname);
            statement.execute(query);
            return true;
        }catch(SQLException e){
            logger.error("Failed to execute add element query" + e.getMessage());
        }
        return false;
    }
    public List<InjectionView> searchForElement(String surname){
        try(Connection conn = DataSourceConfig.getConnection();
            Statement statement = conn.createStatement()
        ){
            String query = String.format("SELECT first_name, surname FROM airport.injection_training WHERE surname='%s'", surname);
            ResultSet rs = statement.executeQuery(query);
            return mapToInjectionView(rs);
        }catch(SQLException e){
            logger.error("Failed to execute search query" + e.getMessage());
        }
        return null;
    }
    public List<InjectionView> showAll(){
        try(Connection conn = DataSourceConfig.getConnection();
        Statement statement = conn.createStatement()
    ){
        String query = String.format("SELECT first_name, surname FROM airport.injection_training;");
        ResultSet rs = statement.executeQuery(query);
        return mapToInjectionView(rs);
    }catch(SQLException e){
        logger.error("Failed to execute search query" + e.getMessage());
    }
    return null;
    }
    private List<InjectionView> mapToInjectionView(ResultSet rs){
        List<InjectionView> elements = new ArrayList<>();
        try{
            while(rs.next()){
                InjectionView element = new InjectionView();
                element.setName(rs.getString("first_name"));
                element.setSurname(rs.getString("surname"));
                elements.add(element);
            }
            return elements;
        }catch(SQLException e){
            logger.error("Failed to execute search query" + e.getMessage());
        }
        return null;
    }
}
