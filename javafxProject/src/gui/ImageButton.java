package gui;

import com.jfoenix.controls.JFXButton;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Super type for buttons with images on them
 */
public class ImageButton extends JFXButton {
    private final int imgPixelLength;
    private final Image selectedImg;

    public ImageButton(int imgPixelLength, Image selectedImg) {
        this.imgPixelLength = imgPixelLength;
        this.selectedImg = selectedImg;
    }

    public void selectButton(boolean bool) {
        Node graphic = bool ? new ImageView(selectedImg) : null;
        this.setGraphic(graphic);
    }

    public void invertGraphic() {
        selectButton(this.getGraphic() == null);
    }
}
