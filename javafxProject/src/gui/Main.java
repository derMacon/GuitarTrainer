package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Main method starting the gui application
 */
public class Main extends Application {

    private static final int WIDTH = 1346;
    private static final int HEIGHT = 700;
    private static final int MIN_HEIGHT = 700;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Pop up window alert when an exception is not handled
        // todo delete before final commit
        Thread.currentThread().setUncaughtExceptionHandler((Thread th, Throwable ex) -> {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Unhandled exception - Please report");
            alert.showAndWait();
        });

        Parent root = FXMLLoader.load(getClass().getResource("GuitarFretboard.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("gui/jfoenixTheme.css");

        stage.setScene(scene);
        stage.setMinWidth(WIDTH);
        stage.setMaxWidth(HEIGHT);
        stage.setMinHeight(MIN_HEIGHT);
        stage.setTitle("Guitar Trainer");
        stage.getIcons().add(new Image("textures\\GTLogoAlpha.png"));
        stage.show();
    }

}
