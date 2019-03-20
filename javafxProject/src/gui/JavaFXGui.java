package gui;

import com.jfoenix.controls.JFXButton;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import logic.organization.GUIConnector;
import logic.guitar.Note;

public class JavaFXGui implements GUIConnector {

    /**
     * Gridpane array of one dimensional gridpanes representing the frets of the users guitar on the gui. Index mirrors
     * fret num
     */
    private final GridPane[] frets;
    private final GridPane sheetNotes;
    private final JFXButton replayBtn;

    public JavaFXGui(GridPane[] fretboard, GridPane sheet, JFXButton replayBtn) {
        this.frets = fretboard;
        this.sheetNotes = sheet;
        this.replayBtn = replayBtn;
    }

    @Override
    public void updateGuitar(Note currNote) {
        GuitarJFXButton currBtn = null;
        for (int idxFret = 0; idxFret < this.frets.length; idxFret++) {
            currBtn = (GuitarJFXButton) this.frets[idxFret].getChildren().get(currNote.getBaseString());
            currBtn.selectButton(currNote.isPlayed() && currNote.getFretboardPos().getFret() == idxFret);
        }
    }

    @Override
    public void updateSheetNotes(Note currNote) {
        // todo
    }

    @Override
    public void setReplayButtonGrayedout(boolean isGrayedout) {
        replayBtn.setDisable(isGrayedout);
    }

    @Override
    public void initGui() {
        for (Node curr : frets[0].getChildren()) {
            assert curr instanceof GuitarJFXButton;
            ((GuitarJFXButton) curr).selectButton(true);
        }
    }

}
