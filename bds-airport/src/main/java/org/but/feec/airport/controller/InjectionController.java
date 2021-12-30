package org.but.feec.airport.controller;

import java.util.List;

import org.but.feec.airport.App;
import org.but.feec.airport.api.InjectionView;
import org.but.feec.airport.data.InjectionRepository;
import org.but.feec.airport.service.InjectionService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;

import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InjectionController {
    private static final Logger logger = LoggerFactory.getLogger(InjectionController.class);
    @FXML
    private Button addButton;

    @FXML
    private Button searchButton;

    @FXML
    private Button refreshButton;

    @FXML
    private TextField surnameField;

    @FXML
    private TableView<InjectionView> injectionView;

    @FXML
    private TableColumn<InjectionView, String> elementName;

    @FXML
    private TableColumn<InjectionView, String> elementSurname;

    private InjectionRepository injectionRepository;
    private InjectionService injectionService;


    @FXML
    void initialize(){
        elementName.setCellValueFactory(new PropertyValueFactory<InjectionView, String>("name"));
        elementSurname.setCellValueFactory(new PropertyValueFactory<InjectionView, String>("surname"));
        initializeServices();
        showAll();
        logger.info("InjectionController initialized");
    }

    @FXML
    void handleAdd(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        loadFxml(fxmlLoader, "fxml/AddElement.fxml", "Add element");
    }

    @FXML
    void handleSearch(ActionEvent event) {
        ObservableList<InjectionView> searchResult = FXCollections.observableArrayList(injectionService.findElement(surnameField.getText()));
        injectionView.getItems().clear();
        injectionView.setItems(searchResult);
    }
    @FXML
    void handleRefresh(ActionEvent event) {
        injectionView.getItems().clear();
        showAll();
    }
    @FXML
    void switchToPassengers(){
        loadFxml(new FXMLLoader(), "fxml/AdminView.fxml", "Passengers");
        Stage stageOld = (Stage) addButton.getScene().getWindow();
        stageOld.close();
    }
    @FXML 
    void switchToEmployees(){

    }
    private void initializeServices(){
        this.injectionRepository = new InjectionRepository();
        this.injectionService = new InjectionService(injectionRepository); 
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

    private void showAll(){
        ObservableList<InjectionView> injectionViews = FXCollections.observableArrayList(injectionService.showAll());
        injectionView.setItems(injectionViews);
    }

}
