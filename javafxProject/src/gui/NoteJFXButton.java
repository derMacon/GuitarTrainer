package gui;

import javafx.scene.image.Image;

public class NoteJFXButton extends ImageButton {

    private int offset;
    private NotePrefix prefix;

    public NoteJFXButton(int imgPixelLength, Image selectedImg) {
        super(imgPixelLength, selectedImg);
    }


}
