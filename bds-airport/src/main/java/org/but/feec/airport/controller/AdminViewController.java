package org.but.feec.airport.controller;

import java.util.List;

import org.but.feec.airport.api.AdminView;
import org.but.feec.airport.data.*;
import org.but.feec.airport.service.AdminService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminViewController {

    @FXML
    private TableView<AdminView> adminsView;

    @FXML
    private TableColumn<AdminView, Long> contactID;

    @FXML
    private TableColumn<AdminView, String> passengerName;

    @FXML
    private TableColumn<AdminView, String> passengerPassword;

    @FXML
    private TableColumn<AdminView, String> passengerPhoneNumber;

    @FXML
    private TableColumn<AdminView, String> passengerSurname;

    @FXML
    private TableColumn<AdminView, String> passengerEmail;

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

    private AdminRepository adminRepository;
    private AdminService adminService;

    @FXML
    private void initialize(){
        passengerName.setCellValueFactory(new PropertyValueFactory<AdminView, String>("name"));
        passengerSurname.setCellValueFactory(new PropertyValueFactory<AdminView, String>("surname"));
        contactID.setCellValueFactory(new PropertyValueFactory<AdminView, Long>("contactId"));
        passengerPhoneNumber.setCellValueFactory(new PropertyValueFactory<AdminView, String>("phoneNumber"));
        passengerEmail.setCellValueFactory(new PropertyValueFactory<AdminView, String>("email"));
        passengerPassword.setCellValueFactory(new PropertyValueFactory<AdminView, String>("password"));

        initializeServices();
        List<AdminView> list = adminService.showAllPassengers();
        ObservableList<AdminView> adminViewList = FXCollections.observableArrayList(list);
        adminsView.setItems(adminViewList);
   
    }
    private void initializeServices(){
        this.adminRepository = new AdminRepository();
        this.adminService = new AdminService(adminRepository);
    }
    
    @FXML
    void handleAddPassenger(ActionEvent event) {

    }

    @FXML
    void handleDeletePassenger(ActionEvent event) {

    }

    @FXML
    void handleOnSerach(ActionEvent event) {

    }

    @FXML
    void handleRefresh(ActionEvent event) {

    }

    @FXML
    void handleShowAll(ActionEvent event) {

    }

    @FXML
    void handleUpdate(ActionEvent event) {

    }

}


