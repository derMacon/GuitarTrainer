package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Main method starting the gui application
 */
public class Main extends Application {

    private static final int WIDTH = 1346;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("GuitarFretboard.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("gui/jfoenixTheme.css");

        stage.setScene(scene);
        stage.setMinWidth(WIDTH);
        stage.setMaxWidth(WIDTH);
        stage.setTitle("Guitar Trainer");
        stage.getIcons().add(new Image("textures\\GTLogoAlpha.png"));
        stage.show();
    }

}
