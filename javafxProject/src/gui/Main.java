package gui;

import de.jensd.fx.glyphs.GlyphIcon;
import de.jensd.fx.glyphs.GlyphsBuilder;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
<<<<<<< HEAD
import javafx.scene.image.ImageView;
=======
>>>>>>> c5bdcd5c1bf5325939205f01bb3e47528a39b66f
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
        stage.getIcons().add(new Image("textures\\GTLogoAlpha.png"));
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
