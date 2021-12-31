package org.but.feec.airport.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import javafx.scene.control.ComboBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.event.EventHandler;


import org.but.feec.airport.api.TicketView;
import org.but.feec.airport.service.EmailHolderService;
import org.but.feec.airport.service.TicketService;
import org.but.feec.airport.data.TicketRepository;

public class BookingController {
    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    @FXML
    private Button bookButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextField destinationField;

    @FXML
    private TableView<TicketView> bookingTable;

    @FXML
    private TableColumn<TicketView, String> departureTime;

    @FXML
    private TableColumn<TicketView, String> destination;

    @FXML
    private TableColumn<TicketView, Long> flightId;

    @FXML
    private TableColumn<TicketView, String> gate;

    @FXML
    private TableColumn<TicketView, String> airline;


    @FXML
    private ComboBox<String> classBox;


    private TicketRepository ticketRepository;
    private TicketService ticketService;
    private ValidationSupport validation;

    @FXML
    public void initialize(){

        flightId.setCellValueFactory(new PropertyValueFactory<TicketView, Long>("idFlight"));
        gate.setCellValueFactory(new PropertyValueFactory<TicketView, String>("gate"));
        destination.setCellValueFactory(new PropertyValueFactory<TicketView, String>("destination"));
        departureTime.setCellValueFactory(new PropertyValueFactory<TicketView, String>("departureTime"));
        airline.setCellValueFactory(new PropertyValueFactory<TicketView, String>("airline"));

        initializeServices();
        initializeValidations();
        initializeFlightClass();
    }
    @FXML
    void hadnleSearch(ActionEvent event) {
        showAvailableTickets();
    }

    @FXML
    void handleBooking(ActionEvent event) {
        TicketView ticketView = bookingTable.getSelectionModel().getSelectedItem();
        String flightClass = classBox.getSelectionModel().getSelectedItem();
        String email = EmailHolderService.getInstance().getEmail();
        if(ticketView == null || flightClass == null){
            handleFailedQuery("You need to select both a ticket and class");
        }
        else{
            boolean result = ticketService.bookTicket(ticketView, flightClass, email);
            if(result){
                handleSuccessfulQuery("Ticket successfuly booked");
                Stage stage = (Stage) bookButton.getScene().getWindow();
                stage.close();
            }
            else{
                handleFailedQuery("Failed to book the ticket");
            }
        }

    }

    private void initializeServices(){
        this.ticketRepository = new TicketRepository();
        this.ticketService = new TicketService(ticketRepository);
    }
    private void initializeValidations(){
        validation = new ValidationSupport();
        validation.registerValidator(destinationField, Validator.createEmptyValidator("The username must not be empty."));
        searchButton.disableProperty().bind(validation.invalidProperty());
        bookButton.disableProperty().bind(validation.invalidProperty());
    }
    private void initializeFlightClass(){
        try{
            ObservableList<String> classes = FXCollections.observableArrayList(ticketService.loadFlightClasses());
            classBox.setItems(classes);
        }catch(NullPointerException e){

        }
    }
    private void showAvailableTickets(){
        try{
            ObservableList<TicketView> tickets = FXCollections.observableArrayList(ticketService.searchForTickets(destinationField.getText()));
            bookingTable.setItems(tickets);
        }catch(Exception e){
            logger.error(e.getMessage());
        }
    }
    private void handleSuccessfulQuery(String queryDescription){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Query successful");
        alert.setHeaderText("Query successful");
        alert.setContentText(queryDescription);

        Timeline idlestage = new Timeline(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                alert.setResult(ButtonType.CANCEL);
                alert.hide();
            }
        }));
        idlestage.setCycleCount(1);
        idlestage.play();

        alert.showAndWait();
    }
    private void handleFailedQuery(String queryDescription){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Query failed");
        alert.setHeaderText("Query failed");
        alert.setContentText(queryDescription);
        
        Timeline idlestage = new Timeline(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                alert.setResult(ButtonType.CANCEL);
                alert.hide();
            }
        }));
        idlestage.setCycleCount(1);
        idlestage.play();

        alert.showAndWait();
    }

}