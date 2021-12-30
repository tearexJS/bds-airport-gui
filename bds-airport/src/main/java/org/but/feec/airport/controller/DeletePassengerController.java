package org.but.feec.airport.controller;

import org.but.feec.airport.data.PassengerRepository;
import org.but.feec.airport.service.PassengerService;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.EventHandler;

public class DeletePassengerController {
    private static final Logger logger = LoggerFactory.getLogger(DeletePassengerController.class);
    @FXML
    private TextField emailField;

    @FXML
    private Button submit;

    private ValidationSupport validation;
    private PassengerRepository passengerRepository;
    private PassengerService passengerService;

    @FXML
    public void initialize(){
        initializeValidations();
        initializeServices();
        logger.info("DeletePassengerController initialized");
    }

    @FXML  
    void handlePassengerDelete(ActionEvent event) {
        boolean result = passengerService.deletePassenger(emailField.getText());
        if(result){
            handleSuccessfulQuery("Passenger deleted successfuly");
            Stage stageOld = (Stage) submit.getScene().getWindow();
            stageOld.close();
        }
        else{
            handleFailedQuery("Failed to delete passenger");
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
    private void initializeServices(){
        passengerRepository = new PassengerRepository();
        passengerService = new PassengerService(passengerRepository);
    }
    private void initializeValidations(){
        validation = new ValidationSupport();
        validation.registerValidator(emailField, Validator.createEmptyValidator("The email must not be empty."));
        submit.disableProperty().bind(validation.invalidProperty());
    }
}
