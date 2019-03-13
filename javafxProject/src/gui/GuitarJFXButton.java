package gui;

import com.jfoenix.controls.JFXButton;

public class GuitarJFXButton extends JFXButton {

    private int guitarString;
    private int guitarFret;

    GuitarJFXButton(int guitarString, int guitarFret) {
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
