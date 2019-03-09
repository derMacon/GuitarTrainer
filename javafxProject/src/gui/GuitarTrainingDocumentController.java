package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import logic.guitar.Guitar;

import java.net.URL;
import java.util.ResourceBundle;

public class GuitarTrainingDocumentController implements Initializable {

    @FXML
    private GridPane grdPnMiddleBox;

    @FXML
    private GridPane grdPnButtons;


    private static final Image IMG_GUITAR_FRET = new Image("textures\\guitarBaseTexture.png");

    private Guitar guitar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPnWithImage(grdPnMiddleBox, this.IMG_GUITAR_FRET, false);
        fillFretWithButtons();


        this.guitar = new Guitar();
    }

    private void fillFretWithButtons() {
        for (int fretNum = 0; fretNum < this.grdPnButtons.getRowConstraints().size(); fretNum++) {
            for (int currString = 0; currString < 6; currString++) {
                this.grdPnButtons.add(createButton("btn_s" + currString + "_f" + fretNum), fretNum, currString);
            }
        }
    }

    private JFXRadioButton createButton(String id) {
        JFXRadioButton button = new JFXRadioButton();
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        button.setId(id);
        button.setOnAction(e -> buttonPressed(id));
        return button;
    }

    private void setPnWithImage(Pane pane, Image image, boolean addAsForeground) {
        ImageView imgVW = new ImageView(image);
        imgVW.setPreserveRatio(false);
        imgVW.fitHeightProperty().bind(pane.heightProperty());
        imgVW.fitWidthProperty().bind(pane.widthProperty());
        if (addAsForeground) {
            pane.getChildren().add(imgVW);
        } else {
            pane.getChildren().add(0, imgVW);
        }
    }

    public void buttonPressed(String buttonId) {
        System.out.println("Button pressed by: " + buttonId);
    }

}
