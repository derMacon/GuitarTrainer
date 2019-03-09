package gui;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class GuitarTrainingDocumentController implements Initializable {

    @FXML
    private GridPane grdPnMiddleBox;

    @FXML
    private GridPane grdPnButtons;


    private static final Image IMG_GUITAR_FRET = new Image("textures\\guitarBaseTexture.png");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPnWithImage(grdPnMiddleBox, this.IMG_GUITAR_FRET, false);
        fillFretWithButtons();
    }

    private void fillFretWithButtons() {
        for (int fretNum = 0; fretNum < this.grdPnButtons.getRowConstraints().size(); fretNum++) {
            for (int currString = 0; currString < 5; currString++) {
                this.grdPnButtons.add(createButton("btn_s" + currString + "_f" + fretNum), fretNum, currString);
            }
        }
    }

    private JFXButton createButton(String id) {
        JFXButton button = new JFXButton();
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        button.setId(id);
        button.setOnAction(e -> buttonPressed(id));
        return button ;
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
