package gui;

import com.jfoenix.controls.JFXButton;
import javafx.scene.image.ImageView;

public class SheetPrefixJFXButton extends JFXButton {

    private int lineOffset;
    private Prefix del;

    public SheetPrefixJFXButton(int offset) {
        this.lineOffset = offset;
        del = Prefix.NEUTRAL;
    }

    public void iterateButton() {
        del = Prefix.values()[(del.ordinal() + 1) % Prefix.values().length];
        this.setGraphic(new ImageView(del.getImg()));
    }

    public int getLineOffset() {
        return lineOffset;
    }

    public Prefix getPrefix() {
        return del;
    }
}
