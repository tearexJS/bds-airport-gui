package org.but.feec.airport.controller;

import org.but.feec.airport.api.TicketView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class TicketViewController {

    @FXML
    private Button bookButton;

    @FXML
    private TableColumn<TicketView, Double> cost;

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<TicketView, String> destination;

    @FXML
    private TableColumn<TicketView, Long> flightId;

    @FXML
    private TableColumn<TicketView, String> gate;

    @FXML
    private TableView<TicketView> ticketTable;
    private String email;

    @FXML
    void initialize(){
        Stage stage = (Stage) bookButton.getScene().getWindow();
        this.email = (String) stage.getUserData();
    }

    @FXML
    void handleBooking(ActionEvent event) {

    }
    @FXML
    void handleDelete(ActionEvent event) {

    }

    private void showPassengersTickets(){
        
    }
}