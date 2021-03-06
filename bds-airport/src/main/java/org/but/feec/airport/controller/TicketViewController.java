package org.but.feec.airport.controller;

import org.but.feec.airport.App;
import org.but.feec.airport.api.TicketView;
import org.but.feec.airport.data.TicketRepository;
import org.but.feec.airport.service.EmailHolderService;
import org.but.feec.airport.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.event.EventHandler;

public class TicketViewController {
    private static final Logger logger = LoggerFactory.getLogger(TicketViewController.class);

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

        flightId.setCellValueFactory(new PropertyValueFactory<TicketView, Long>("idFlight"));
        gate.setCellValueFactory(new PropertyValueFactory<TicketView, String>("gate"));
        destination.setCellValueFactory(new PropertyValueFactory<TicketView, String>("destination"));
        cost.setCellValueFactory(new PropertyValueFactory<TicketView, Double>("cost"));
        airlineName.setCellValueFactory(new PropertyValueFactory<TicketView, String>("airline"));
        flightClass.setCellValueFactory(new PropertyValueFactory<TicketView, String>("flightClass"));
        email = EmailHolderService.getInstance().getEmail();

        initializeServices();
        showPassengersTickets();


    }

    @FXML
    void handleBooking(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        loadFxml(fxmlLoader, "fxml/BookingView.fxml", "Booking");
    }
    @FXML
    void handleDelete(ActionEvent event) {
        TicketView ticket = ticketTable.getSelectionModel().getSelectedItem();
        String email = EmailHolderService.getInstance().getEmail();
        if(ticket == null){
            handleFailedQuery("You need to select both ticket and class");
        }
        else{
            boolean result = ticketService.deleteTicket(ticket, email);
            if(result){
                handleSuccessfulQuery("Ticket deleted successfuly");
            }
            else{
                handleFailedQuery("Failed to delete ticket");
            }
        }
    }

    private void showPassengersTickets(){
        try{
            ObservableList<TicketView> tickets = FXCollections.observableArrayList(ticketService.showPassengersTickets(email));
            ticketTable.setItems(tickets);
        }catch(Exception e){
            logger.error(e.getMessage());
        }
    }
    private void initializeServices(){
        this.ticketRepository = new TicketRepository();
        this.ticketService = new TicketService(ticketRepository);
    }
    private void loadFxml(FXMLLoader fxmlLoader, String path, String title){
        try{
            fxmlLoader.setLocation(App.class.getResource(path));

            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();

            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
            logger.error("Authentication successful but a FXML loading error occured", e.getMessage());
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