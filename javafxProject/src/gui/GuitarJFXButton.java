package gui;

import javafx.scene.image.Image;

/**
 * Class implementing the buttons on the instrument fretboard. Provides the necessary mehtods to invert the graphic.
 */
public class GuitarJFXButton extends ImageButton {

    private static final int SELECTED_PIXEL = 20;
    private static final Image SELECTED_SYMBOL = new Image("/textures/selected.png", SELECTED_PIXEL,
            SELECTED_PIXEL, true, true);

    private int guitarString;
    private int guitarFret;

    /**
     * Constructor setting the instrument string and fret
     *
     * @param guitarString instrument string the button is located on
     * @param guitarFret   instrument fret the button is located on
     */
    GuitarJFXButton(int guitarString, int guitarFret) {
        super(SELECTED_PIXEL, SELECTED_SYMBOL);
        this.guitarString = guitarString;
        this.guitarFret = guitarFret;
    }

    /**
     * Getter for the instrument string
     *
     * @return the instrument string the button is located on
     */
    public int getGuitarString() {
        return guitarString;
    }

    /**
     * Getter for the instrument fret
     *
     * @return the instrument fret the button is located on
     */
    public int getGuitarFret() {
        return guitarFret;
    }
}
