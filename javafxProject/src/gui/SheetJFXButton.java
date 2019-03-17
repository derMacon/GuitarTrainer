package gui;

import com.jfoenix.controls.JFXButton;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SheetJFXButton extends JFXButton {

    private final static int SELECTED_PIXEL = 20;
    private final static Image SELECTED_SYMBOL = new Image("textures\\selected.png", SELECTED_PIXEL,
            SELECTED_PIXEL,true, true);

    /**
     * Offset of the button compared to the lowest possible note e (octave = 0)
     */
    private final int lineOffset;

    public SheetJFXButton(int lineOffset) {
        this.lineOffset = lineOffset;
    }

    public void pressNote(boolean bool) {
        Node graphic = bool ? new ImageView(SELECTED_SYMBOL) : null;
        this.setGraphic(graphic);
    }

    public void invertGraphic() {
        pressNote(this.getGraphic() == null);
    }
}
