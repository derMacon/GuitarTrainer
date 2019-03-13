package gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GuitarFretboardController implements Initializable {

    @FXML
    private ImageView imgBase;

    @FXML
    private AnchorPane imgParent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image img = new Image("textures\\guitarGui3.png");
        this.imgBase.setImage(new Image("textures\\guitarGui3.png"));
        // https://stackoverflow.com/questions/12630296/resizing-images-to-fit-the-parent-node
        this.imgBase.fitWidthProperty().bind(this.imgParent.widthProperty());

    }

}
