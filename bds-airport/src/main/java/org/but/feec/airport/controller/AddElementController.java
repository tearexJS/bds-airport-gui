package org.but.feec.airport.controller;

import org.but.feec.airport.data.InjectionRepository;
import org.but.feec.airport.service.InjectionService;
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

public class AddElementController {
    private static final Logger logger = LoggerFactory.getLogger(AdminViewController.class);
    @FXML
    private TextField nameField;

    @FXML
    private Button submit;

    @FXML
    private TextField surnameField;
    
    private InjectionRepository injectionRepository;
    private InjectionService injectionService;
    private ValidationSupport validation;
    
    @FXML
    void initialize(){
        initializeServices();
        initializeValidations();
        logger.info("AddElementController initialized successfuly");
    }

    @FXML
    void handleSubmit(ActionEvent event) {
        boolean result = injectionService.addElement(nameField.getText(), surnameField.getText());
        if(result){
            handleSuccessfulQuery("Element added successfuly");
            Stage stageOld = (Stage) submit.getScene().getWindow();
            stageOld.close();
        }
        else{
            handleFailedQuery("Failed to add element");
        }
    }
    private void initializeServices(){
        this.injectionRepository = new InjectionRepository();
        this.injectionService = new InjectionService(injectionRepository);
    }

    private void initializeValidations(){
        validation = new ValidationSupport();
        validation.registerValidator(nameField, Validator.createEmptyValidator("In order to add it's required to fill this field"));
        validation.registerValidator(surnameField, Validator.createEmptyValidator("In order to add it's required to fill this field"));
        submit.disableProperty().bind(validation.invalidProperty());
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
