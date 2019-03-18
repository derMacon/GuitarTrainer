package gui;

import javafx.scene.image.Image;

public enum NotePrefix {
    // TODO update selected textures
    NEUTRAL(null),
    MAJOR("textures\\selected.png"),
    FLAT("textures\\selected.png");

    private final static int SELECTED_PIXEL = 5;

    private String imgPath;

    NotePrefix(String imgPath) {
        this.imgPath = imgPath;
    }

    public Image getImg() {
        return new Image(imgPath, SELECTED_PIXEL, SELECTED_PIXEL, true, true);
    }
}
