package gui;

import javafx.scene.image.Image;

public enum NotePrefix {
    FLAT("sheetNotes\\prefix_flat.png"),
    NEUTRAL("sheetNotes\\prefix_neutral.png"),
    SHARP("sheetNotes\\prefix_sharp.png"),
    MUTED(null);

    private final static int SELECTED_PIXEL = 20;

    private String imgPath;

    NotePrefix(String imgPath) {
        this.imgPath = imgPath;
    }

    public Image getImg() {
        return new Image(imgPath, SELECTED_PIXEL, SELECTED_PIXEL, true, true);
    }
}
