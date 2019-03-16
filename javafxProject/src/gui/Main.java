package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("GuitarTrainerFXML.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("GuitarFretboard.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("gui/jfoenixTheme.css");

        stage.setScene(scene);
        stage.setMinWidth(1346);
        stage.setMaxWidth(1346);
        stage.setTitle("Guitar Trainer");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
