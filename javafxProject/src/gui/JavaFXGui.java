package gui;

import com.jfoenix.controls.JFXButton;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import logic.guitar.SheetNote;
import logic.organization.GUIConnector;
import logic.guitar.FretboardNote;

public class JavaFXGui implements GUIConnector {

    private static final int NOTES_BETWEEN_LINES = 0;
    private static final int NOTES_ONTOP_LINES = 1;

    /**
     * Gridpane array of one dimensional gridpanes representing the frets of the users guitar on the gui. Index mirrors
     * fret num
     */
    private final GridPane[] frets;
    private final GridPane[] sheetNotes;
    private final JFXButton replayBtn;

    public JavaFXGui(GridPane[] fretboard, GridPane[] sheetNotes, JFXButton replayBtn) {
        this.frets = fretboard;
        this.sheetNotes = sheetNotes;
        this.replayBtn = replayBtn;
    }

    @Override
    public void updateGuitar(FretboardNote currFretboardNote) {
        GuitarJFXButton currBtn = null;
        for (int idxFret = 0; idxFret < this.frets.length; idxFret++) {
            currBtn = (GuitarJFXButton) this.frets[idxFret].getChildren().get(currFretboardNote.getBaseString());
            currBtn.selectButton(currFretboardNote.isPlayed() && currFretboardNote.getFretboardPos().getFret() == idxFret);
        }
    }

    @Override
    public void updateSheetNotes(SheetNote sheetNote) {
        // note below / between lines
        if(sheetNote.getId().ordinal() % 2 == 0) {
            int spaceCnt = this.sheetNotes[0].getRowConstraints().size();
            int invertedOffset = spaceCnt - 1 - sheetNote.getOffsetToLowerE();
            Node node = getNodeFromGridPane(this.sheetNotes[0], 1, invertedOffset);
            ((SheetNoteJFXButton)node).invertGraphic();
        } else {
            System.out.println("Wrong branch");
        }

    }

    /**
     * https://stackoverflow.com/questions/20655024/javafx-gridpane-retrieve-specific-cell-content
     * @param gridPane
     * @param col
     * @param row
     * @return
     */
    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
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
