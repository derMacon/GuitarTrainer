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

    /**
     * Constructor setting up the imageview dimensions and a actual image
     * @param imgPixelLength imageview dimensions
     * @param selectedImg image to be set on the imageview
     */
    public ImageButton(int imgPixelLength, Image selectedImg) {
        this.imgPixelLength = imgPixelLength;
        this.selectedImg = selectedImg;
    }

    /**
     * Sets the flag for the selected image
     * @param bool flag to be set
     */
    public void selectButton(boolean bool) {
        Node graphic = bool ? new ImageView(selectedImg) : null;
        this.setGraphic(graphic);
    }

}
