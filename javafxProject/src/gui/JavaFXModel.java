package gui;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import logic.guitar.GUIConnector;
import logic.guitar.Note;

public class JavaFXModel implements GUIConnector {

    private final GridPane[] panes;

    public JavaFXModel(GridPane[] panes) {
        this.panes = panes;
    }

    @Override
    public void updateGui(Note currNote) {
        GuitarJFXButton currBtn = null;
        for (int idxFret = 0; idxFret < this.panes.length; idxFret++) {
            currBtn = (GuitarJFXButton) this.panes[idxFret].getChildren().get(currNote.getBaseString());
            currBtn.pressNote(currNote.isPlayed() && currNote.getPos().getFret() == idxFret);
        }
    }

    @Override
    public void initGui() {
        // todo delete args
        for (Node curr : panes[0].getChildren()) {
            assert curr instanceof GuitarJFXButton;
            ((GuitarJFXButton) curr).pressNote(true);
        }
    }

    @Override
    public String getNoteName() {
        return null;
    }
}
