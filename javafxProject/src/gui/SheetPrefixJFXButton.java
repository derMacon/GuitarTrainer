package gui;

import com.jfoenix.controls.JFXButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.note.Prefix;

public class SheetPrefixJFXButton extends JFXButton {

    // todo maybe delete this class?

    private int lineOffset;
    private Prefix del;

    public SheetPrefixJFXButton(int offset) {
        this.lineOffset = offset;
        del = Prefix.NEUTRAL;
    }

    public Prefix getPrefix() {
        return del;
    }
}
