package gui;

import javafx.scene.image.Image;

public class GuitarJFXButton extends ImageButton {

    private final static int SELECTED_PIXEL = 20;
    private final static Image SELECTED_SYMBOL = new Image("textures\\selected.png", SELECTED_PIXEL,
            SELECTED_PIXEL,true, true);

    private int guitarString;
    private int guitarFret;

    GuitarJFXButton(int guitarString, int guitarFret) {
        super(SELECTED_PIXEL, SELECTED_SYMBOL);
        this.guitarString = guitarString;
        this.guitarFret = guitarFret;
    }

    public int getGuitarString() {
        return guitarString;
    }

    public int getGuitarFret() {
        return guitarFret;
    }
}
