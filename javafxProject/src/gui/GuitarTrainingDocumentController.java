package gui;

import com.jfoenix.controls.JFXRadioButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import logic.guitar.Guitar;
import logic.guitar.Pos;

import java.net.URL;
import java.util.ResourceBundle;

public class GuitarTrainingDocumentController implements Initializable {

    @FXML
    private GridPane grdPnMiddleBox;

    @FXML
    private GridPane grdPnButtons;


    private static final Image IMG_GUITAR_FRET = new Image("textures\\guitarBaseTexture.png");
    public static final String RADIO_BTN_PREFIX = "rdBtn_";

    private Guitar guitar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPnWithImage(grdPnMiddleBox, this.IMG_GUITAR_FRET, false);
        fillFretWithButtons();


        this.guitar = new Guitar(new JavaFXGui(this.grdPnButtons));
    }

    private void fillFretWithButtons() {
        for (int stringIdx = 0; stringIdx < this.grdPnButtons.getColumnConstraints().size(); stringIdx++) {
            for (int fretIdx = 0; fretIdx < this.grdPnButtons.getRowConstraints().size(); fretIdx++) {
                this.grdPnButtons.add(createButton(RADIO_BTN_PREFIX + stringIdx + fretIdx), fretIdx, stringIdx);
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
        if(buttonId.startsWith(RADIO_BTN_PREFIX)) {
            buttonId = buttonId.substring(RADIO_BTN_PREFIX.length());
        }
        System.out.println("Button pressed by: " + buttonId);
        this.guitar.pressNote(new Pos((int) (buttonId.charAt(0) - '0'), Integer.parseInt(buttonId.substring(1))));
    }

}
