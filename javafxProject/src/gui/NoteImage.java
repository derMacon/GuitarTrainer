package gui;

import javafx.scene.image.Image;
import logic.note.Prefix;

public class NoteImage extends Image {

    private int offset;
    private Prefix prefix;

    public NoteImage(String url, int offset, Prefix prefix) {
        super(url);
        this.offset = offset;
        this.prefix = prefix;
    }


}
