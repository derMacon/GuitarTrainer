package gui;

import com.jfoenix.controls.JFXButton;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GuitarJFXButton extends JFXButton {

    private final static int SELECTED_PIXEL = 20;
    private final String IMG_PATH_SELECTED = "textures\\selected.png";

    private final static Image SELECTED_SYMBOL = new Image("textures\\selected.png", SELECTED_PIXEL,
            SELECTED_PIXEL,true, true);

    private int guitarString;
    private int guitarFret;

    GuitarJFXButton(int guitarString, int guitarFret) {
        this.guitarString = guitarString;
        this.guitarFret = guitarFret;
    }

    public void pressNote(boolean bool) {
        Node graphic = bool ? new ImageView(SELECTED_SYMBOL) : null;
        this.setGraphic(graphic);
    }

    public void invertGraphic() {
        pressNote(this.getGraphic() == null);
    }

    public int getGuitarString() {
        return guitarString;
    }

    public int getGuitarFret() {
        return guitarFret;
    }
}
