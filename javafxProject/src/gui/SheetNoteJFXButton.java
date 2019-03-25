package gui;

import javafx.scene.image.Image;

public class SheetNoteJFXButton extends ImageButton {

    private final static int SELECTED_PIXEL = 20;
    private final static Image SELECTED_SYMBOL = new Image("sheetNotes\\note.png", SELECTED_PIXEL + 20,
            SELECTED_PIXEL, true, true);

    /**
     * Offset of the button compared to the lowest possible note e (octave = 0)
     */
    private final int lineOffset;

    public SheetNoteJFXButton(int lineOffset) {
        super(SELECTED_PIXEL, SELECTED_SYMBOL);
        this.lineOffset = lineOffset;
    }

    public int getLineOffset() {
        return lineOffset;
    }


}
