package gui;

import com.jfoenix.controls.JFXButton;
import javafx.scene.image.ImageView;

public class SheetPrefixJFXButton extends JFXButton {

    private int lineOffset;
    private NotePrefix del;

    public SheetPrefixJFXButton(int offset) {
        this.lineOffset = offset;
        del = NotePrefix.NEUTRAL;
    }

    public void iterateButton() {
        del = NotePrefix.values()[(del.ordinal() + 1) % NotePrefix.values().length];
        this.setGraphic(new ImageView(del.getImg()));
    }

    public int getLineOffset() {
        return lineOffset;
    }

    public NotePrefix getPrefix() {
        return del;
    }
}
