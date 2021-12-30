package org.but.feec.airport.controller;

import java.util.List;

import org.but.feec.airport.App;
import org.but.feec.airport.api.Passenger;
import org.but.feec.airport.data.*;
import org.but.feec.airport.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.MenuItem;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminViewController {
    private static final Logger logger = LoggerFactory.getLogger(AdminViewController.class);
    
    @FXML
    private TableView<Passenger> adminsView;

    @FXML
    private TableColumn<Passenger, Long> contactID;

    @FXML
    private TableColumn<Passenger, String> passengerName;

    @FXML
    private TableColumn<Passenger, String> passengerPassword;

    @FXML
    private TableColumn<Passenger, String> passengerPhoneNumber;

    @FXML
    private TableColumn<Passenger, String> passengerSurname;

    @FXML
    private TableColumn<Passenger, String> passengerEmail;

    @FXML
    private Button refresh;

    @FXML
    private Button search;

    @FXML
    private Button showAll;

    @FXML
    private Button update;
        
    @FXML
    private Button addPassenger;

    @FXML
    private Button deletePassenger;

    @FXML
    private TextField surnameField;

    @FXML
    private MenuItem employeeTable;

    @FXML
    private MenuItem injectionTable;

    private AdminRepository adminRepository;
    private AdminService adminService;
    private ValidationSupport validation;

    @FXML
    private void initialize(){
        passengerName.setCellValueFactory(new PropertyValueFactory<Passenger, String>("name"));
        passengerSurname.setCellValueFactory(new PropertyValueFactory<Passenger, String>("surname"));
        contactID.setCellValueFactory(new PropertyValueFactory<Passenger, Long>("contactId"));
        passengerPhoneNumber.setCellValueFactory(new PropertyValueFactory<Passenger, String>("phoneNumber"));
        passengerEmail.setCellValueFactory(new PropertyValueFactory<Passenger, String>("email"));
        passengerPassword.setCellValueFactory(new PropertyValueFactory<Passenger, String>("password"));

        initializeServices();
        initializeValidations();
        List<Passenger> list = adminService.showAllPassengers();
        ObservableList<Passenger> adminViewList = FXCollections.observableArrayList(list);
        adminsView.setItems(adminViewList);
    }
    private void initializeServices(){
        this.adminRepository = new AdminRepository();
        this.adminService = new AdminService(adminRepository);
    }
    private void initializeValidations(){
        validation = new ValidationSupport();
        validation.registerValidator(surnameField, Validator.createEmptyValidator("In order to search it's required to fill this field"));
        search.disableProperty().bind(validation.invalidProperty());
    }
    @FXML
    void handleAddPassenger(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        loadFxml(fxmlLoader,"fxml/CreatePassenger.fxml", "Add passenger");
    }

    @FXML
    void handleDeletePassenger(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        loadFxml(fxmlLoader, "fxml/DeletePassenger.fxml", "Delete passenger");
    }

    @FXML
    void handleOnSearch(ActionEvent event) {
        List<Passenger> list = adminService.findPassengers(surnameField.getText());
        ObservableList<Passenger> adminViewList = FXCollections.observableArrayList(list);
        adminsView.getItems().clear();
        adminsView.setItems(adminViewList);
    }

    @FXML
    void handleRefresh(ActionEvent event) {
        List<Passenger> list = adminService.showAllPassengers();
        ObservableList<Passenger> adminViewList = FXCollections.observableArrayList(list);
        adminsView.getItems().clear();
        adminsView.setItems(adminViewList);
    }
    //TODO: delete this
    @FXML
    void handleShowAll(ActionEvent event) {

    }

    @FXML
    void handleUpdate(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        loadFxml(fxmlLoader, "fxml/UpdateEmail.fxml", "Change email");
    }
    @FXML
    void switchToEmployees(ActionEvent event) {

    }

    @FXML
    void switchToInjection(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        loadFxml(fxmlLoader, "fxml/InjectionView.fxml", "Injection training");
        Stage stageOld = (Stage) update.getScene().getWindow();
        stageOld.close();
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

}


