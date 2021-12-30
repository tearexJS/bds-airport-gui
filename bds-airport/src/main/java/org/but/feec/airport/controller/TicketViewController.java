package org.but.feec.airport.controller;

import org.but.feec.airport.api.TicketView;
import org.but.feec.airport.data.TicketRepository;
import org.but.feec.airport.service.EmailHolderService;
import org.but.feec.airport.service.TicketService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TicketViewController {

    @FXML
    private Button bookButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<TicketView, Double> cost;

    @FXML
    private TableColumn<TicketView, String> destination;

    @FXML
    private TableColumn<TicketView, Long> flightId;

    @FXML
    private TableColumn<TicketView, String> gate;

    @FXML
    private TableColumn<TicketView, String> airlineName;

    @FXML
    private TableColumn<TicketView, String> flightClass;

    @FXML
    private TableView<TicketView> ticketTable;
    
    private String email;
    private TicketRepository ticketRepository;
    private TicketService ticketService;


    @FXML
    void initialize(){

        flightId.setCellValueFactory(new PropertyValueFactory<TicketView, Long>("Flight ID"));
        gate.setCellValueFactory(new PropertyValueFactory<TicketView, String>("Gate"));
        destination.setCellValueFactory(new PropertyValueFactory<TicketView, String>("Destination"));
        cost.setCellValueFactory(new PropertyValueFactory<TicketView, Double>("Cost"));
        airlineName.setCellValueFactory(new PropertyValueFactory<TicketView, String>("Airline"));
        flightClass.setCellValueFactory(new PropertyValueFactory<TicketView, String>("Class"));
        initializeServices();
        showPassengersTickets();

        email = EmailHolderService.getInstance().getEmail();
    }

    @FXML
    void handleBooking(ActionEvent event) {

    }
    @FXML
    void handleDelete(ActionEvent event) {

    }

    private void showPassengersTickets(){
        ObservableList<TicketView> tickets = FXCollections.observableArrayList(ticketService.showPassengersTickets(email));
        ticketTable.setItems(tickets);
    }
    private void initializeServices(){
        this.ticketRepository = new TicketRepository();
        this.ticketService = new TicketService(ticketRepository);
    }
}