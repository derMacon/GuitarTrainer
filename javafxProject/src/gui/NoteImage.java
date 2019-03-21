package gui;

import javafx.scene.image.Image;

public class NoteImage extends Image {

    private int offset;
    private NotePrefix prefix;

    public NoteImage(String url, int offset, NotePrefix prefix) {
        super(url);
        this.offset = offset;
        this.prefix = prefix;
    }


}
