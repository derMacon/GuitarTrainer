package gui;

import javafx.scene.layout.GridPane;

public class JavaFXGui implements GUIConnector {

    private GridPane pressedNotes;

    public JavaFXGui(GridPane pressedNotes) {
        this.pressedNotes = pressedNotes;
    }

    @Override
    public void updateString() {

    }

    @Override
    public void getNoteName() {

    }
}
