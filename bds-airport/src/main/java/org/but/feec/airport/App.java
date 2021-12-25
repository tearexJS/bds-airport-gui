package org.but.feec.airport;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class App extends Application {
    private FXMLLoader loader;
    private AnchorPane mainStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try { //v aktuálnej triede loaduje xml
            loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            mainStage = loader.load();
            primaryStage.setTitle("BDS Airport");
            Scene scene = new Scene(mainStage);
            setUserAgentStylesheet(STYLESHEET_MODENA);
            //String myStyle = getClass().getResource("css/myStyle.css").toExternalForm();
            //scene.getStylesheets().add(myStyle);

            primaryStage.getIcons().add(new Image(App.class.getResourceAsStream("logos/vut_logo.png")));
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ex) {
           ex.printStackTrace();
        }
    }

}

