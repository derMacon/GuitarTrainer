package gui;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import logic.organization.GUIConnector;
import logic.guitar.Note;

public class JavaFXGui implements GUIConnector {

    /**
     * Gridpane array of one dimensional gridpanes representing the frets of the users guitar on the gui. Index mirrors
     * fret num
     */
    private final GridPane[] frets;

    private final GridPane sheet;

    public JavaFXGui(GridPane[] fretboard, GridPane sheet) {
        this.frets = fretboard;
        this.sheet = sheet;
    }

    @Override
    public void updateGui(Note currNote) {
        GuitarJFXButton currBtn = null;
        for (int idxFret = 0; idxFret < this.frets.length; idxFret++) {
            currBtn = (GuitarJFXButton) this.frets[idxFret].getChildren().get(currNote.getBaseString());
            currBtn.selectButton(currNote.isPlayed() && currNote.getFretboardPos().getFret() == idxFret);
        }
    }

    @Override
    public void initGui() {
        for (Node curr : frets[0].getChildren()) {
            assert curr instanceof GuitarJFXButton;
            ((GuitarJFXButton) curr).selectButton(true);
        }
    }

}
