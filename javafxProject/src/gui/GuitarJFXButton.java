package gui;

import javafx.scene.image.Image;

/**
 * Class implementing the buttons on the guitar fretboard. Provides the necessary mehtods to invert the graphic.
 */
public class GuitarJFXButton extends ImageButton {

    private static final int SELECTED_PIXEL = 20;
    private static final Image SELECTED_SYMBOL = new Image("textures\\selected.png", SELECTED_PIXEL,
            SELECTED_PIXEL, true, true);

    private int guitarString;
    private int guitarFret;

    /**
     * Constructor setting the guitar string and fret
     * @param guitarString guitar string the button is located on
     * @param guitarFret guitar fret the button is located on
     */
    GuitarJFXButton(int guitarString, int guitarFret) {
        super(SELECTED_PIXEL, SELECTED_SYMBOL);
        this.guitarString = guitarString;
        this.guitarFret = guitarFret;
    }

    /**
     * Getter for the guitar string
     * @return the guitar string the button is located on
     */
    public int getGuitarString() {
        return guitarString;
    }

    /**
     * Getter for the guitar fret
     * @return the guitar fret the button is located on
     */
    public int getGuitarFret() {
        return guitarFret;
    }
}
