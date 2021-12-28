package org.but.feec.airport.controller;

import org.but.feec.airport.data.LoginRepository;
import org.but.feec.airport.service.AuthService;
import org.but.feec.airport.App;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.fxml.FXML;

import java.io.IOException;
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @FXML
    private TextField emailField;

    @FXML
    private Label emailLabel;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label passwordLabel;

    private LoginRepository employee;
    private AuthService auth;
    private ValidationSupport validation;

    @FXML
    private void initialize() {
        emailField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleSignIn();
            }
        });
        passwordField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleSignIn();
            }
        });
        initializeValidations();
        initializeServices();
        logger.info("LoginController initialized");
    }
    private void initializeValidations(){
        validation = new ValidationSupport();
        validation.registerValidator(emailField, Validator.createEmptyValidator("The username must not be empty."));
        validation.registerValidator(passwordField, Validator.createEmptyValidator("The password must not be empty."));
        loginButton.disableProperty().bind(validation.invalidProperty());
    }
    private void initializeServices(){
        employee = new LoginRepository();
        auth = new AuthService(employee);
    }
    private void handleSignIn() {
        String email = emailField.getText();
        String password = passwordField.getText();

        try {
            int authenticated = auth.authenticate(email, password);
            switch(authenticated){
                case 1:
                    showAuthSucces();
                    break;
                case 2:
                    showAuthSucces();
                    handleAdminLogin();
                    break;
                default:
                    showAuthFailed();
            }
        } catch (Exception e) {
            showAuthFailed();
        }
    }
    private void showAuthFailed() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Wrong credentials");
        alert.setHeaderText("Wrong login or password!");
        alert.showAndWait();
    }
    private void handlePassengerLogin() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("fxml/Main.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 614, 350);
            Stage stage = new Stage();
            stage.setTitle("Your courses");
            stage.setScene(scene);

            Stage stageOld = (Stage) loginButton.getScene().getWindow();
            stageOld.close();

            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
            logger.error(String.format("Authentication successful but a FXML loading error occured", e.getMessage()));
        }
        showAuthSucces();
    }
    private void handleAdminLogin(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("fxml/AdminView.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 851, 634);
            Stage stage = new Stage();

            stage.setTitle("Passengers");
            stage.setScene(scene);

            Stage stageOld = (Stage) loginButton.getScene().getWindow();
            stageOld.close();

            stage.show();
        }catch(Exception e){
            e.printStackTrace();
            logger.error("Authentication successful but a FXML loading error occured", e.getMessage());
        }


    }
    private void showAuthSucces() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Login successful");
        alert.setHeaderText("Login successful!");
        alert.setContentText("You may proceed to the application now.");

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
    

