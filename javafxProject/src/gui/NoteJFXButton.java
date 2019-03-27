package gui;

import javafx.scene.image.Image;
import logic.note.Prefix;

public class NoteJFXButton extends ImageButton {

    private int offset;
    private Prefix prefix;

    public NoteJFXButton(int imgPixelLength, Image selectedImg) {
        super(imgPixelLength, selectedImg);
    }


}
