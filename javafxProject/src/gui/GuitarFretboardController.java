package gui;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import logic.audio.AudioConverter;
import logic.audio.SoundPack;
import logic.guitar.Guitar;
import logic.guitar.Pos;

import java.net.URL;
import java.util.ResourceBundle;

public class GuitarFretboardController implements Initializable {

    @FXML
    private GridPane grdPn_fret0;

    @FXML
    private GridPane grdPn_fret1;

    @FXML
    private GridPane grdPn_fret2;

    @FXML
    private ImageView imgBase;

    @FXML
    private AnchorPane imgParent;

    @FXML
    private GridPane grdPn_fret17;

    @FXML
    private GridPane grdPn_fret16;

    @FXML
    private GridPane grdPn_fret18;

    @FXML
    private GridPane grdPn_fret9;

    @FXML
    private GridPane grdPn_fret7;

    @FXML
    private GridPane grdPn_fret11;

    @FXML
    private GridPane grdPn_fret8;

    @FXML
    private GridPane grdPn_fret10;

    @FXML
    private GridPane grdPn_fret19;

    @FXML
    private GridPane grdPn_fret5;

    @FXML
    private GridPane grdPn_fret13;

    @FXML
    private GridPane grdPn_fret6;

    @FXML
    private GridPane grdPn_fret12;

    @FXML
    private GridPane grdPn_fret3;

    @FXML
    private GridPane grdPn_fret15;

    @FXML
    private GridPane grdPn_fret4;

    @FXML
    private GridPane grdPn_fret14;


    private final static int SELECTED_PIXEL = 25;
    private final static String BUTTON_NAME_TEMPLATE = "btn_%s_%s";
    private final static Image SELECTED_SYMBOL = new Image("textures\\selected.png", SELECTED_PIXEL, SELECTED_PIXEL, true, true);

    private Guitar guitar;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initGuitarTexture();
        initFrets();

        this.guitar = new Guitar(new JavaFXModel(), new AudioConverter(SoundPack.NYLON));
    }

    private void initGuitarTexture() {
        double parentWidth = this.imgParent.getBoundsInParent().getWidth();
        double parentHeight = this.imgParent.getBoundsInParent().getHeight();

        this.imgParent.setMinHeight(parentHeight);
        this.imgParent.setMinWidth(parentWidth);
        this.imgBase.setImage(new Image("textures\\guitarGui3.png", parentWidth, parentHeight, true, true));
        // https://stackoverflow.com/questions/12630296/resizing-images-to-fit-the-parent-node
        this.imgBase.fitWidthProperty().bind(this.imgParent.widthProperty());
        this.imgBase.fitHeightProperty().bind(this.imgParent.heightProperty());
    }

    private void initFrets() {
        int guitarStringCnt = 6;
        for (int i = 0; i < guitarStringCnt; i++) {
            this.grdPn_fret0.add(createButton(i, 0), 0,  i);
            this.grdPn_fret1.add(createButton(i, 1), 0,  i);
            this.grdPn_fret2.add(createButton(i, 2), 0,  i);
            this.grdPn_fret3.add(createButton(i, 3), 0,  i);
            this.grdPn_fret4.add(createButton(i, 4), 0,  i);
            this.grdPn_fret5.add(createButton(i, 5), 0,  i);
            this.grdPn_fret6.add(createButton(i, 6), 0,  i);
            this.grdPn_fret7.add(createButton(i, 7), 0,  i);
            this.grdPn_fret8.add(createButton(i, 8), 0,  i);
            this.grdPn_fret9.add(createButton(i, 9), 0,  i);
            this.grdPn_fret10.add(createButton(i, 10), 0,  i);
            this.grdPn_fret11.add(createButton(i, 11), 0,  i);
            this.grdPn_fret12.add(createButton(i, 12), 0,  i);
            this.grdPn_fret13.add(createButton(i, 13), 0,  i);
            this.grdPn_fret14.add(createButton(i, 14), 0,  i);
            this.grdPn_fret15.add(createButton(i, 15), 0,  i);
            this.grdPn_fret16.add(createButton(i, 16), 0,  i);
            this.grdPn_fret17.add(createButton(i, 17), 0,  i);
            this.grdPn_fret18.add(createButton(i, 18), 0,  i);
            this.grdPn_fret19.add(createButton(i, 19), 0,  i);
        }
    }

    private GuitarJFXButton createButton(int guitarString, int fret) {
        GuitarJFXButton button = new GuitarJFXButton(guitarString, fret);
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        button.setOnAction(e -> buttonPressed(button));
        return button;
    }

    private void buttonPressed(GuitarJFXButton button) {
        System.out.println(button.getGuitarString() + " " + button.getGuitarFret());

        this.guitar.pressNote(new Pos(button.getGuitarString(), button.getGuitarFret()));

        if(button.getGraphic() == null) {
            button.setGraphic(new ImageView(SELECTED_SYMBOL));
        } else {
            button.setGraphic(null);
        }
    }


}
