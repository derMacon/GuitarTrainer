package gui;

import javafx.scene.image.Image;

public enum NotePrefix {
    NEUTRAL("sheetNotes\\prefix_neutral.png"),
    MAJOR("sheetNotes\\prefix_sharp.png"),
    FLAT("sheetNotes\\prefix_flat.png");

    private final static int SELECTED_PIXEL = 20;

    private String imgPath;

    NotePrefix(String imgPath) {
        this.imgPath = imgPath;
    }

    public Image getImg() {
        return new Image(imgPath, SELECTED_PIXEL, SELECTED_PIXEL, true, true);
    }
}
