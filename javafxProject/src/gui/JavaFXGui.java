package gui;

import com.jfoenix.controls.JFXButton;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import logic.guitar.FretboardNote;
import logic.guitar.SheetNote;
import logic.organization.GUIConnector;

import java.util.ArrayList;

public class JavaFXGui implements GUIConnector {

    private static final int NOTES_BETWEEN_LINES = 0;
    private static final int NOTES_ONTOP_LINES = 1;
    private static final int SELECTED_PIXEL = 20;
    private static final Image NOTE_SHEET_MUSIC = new Image("sheetNotes\\note.png", SELECTED_PIXEL + 20,
            SELECTED_PIXEL, true, true);
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
        GridPane currGrdPn = sheetNote.getTone().ordinal() % 2 == 0 ?
                this.sheetNotes[NOTES_ONTOP_LINES] : this.sheetNotes[NOTES_BETWEEN_LINES];
        System.out.println(sheetNote);
        int spaceCnt = currGrdPn.getRowConstraints().size();
        int invertedOffset = spaceCnt - 1 - (sheetNote.getOffsetToLowestE() / 2);
        System.out.println("invertoffset: " + invertedOffset + ", spaceCnt: " + spaceCnt + ", id: " + sheetNote.getTone());
        ImageView prefix = (ImageView) getNodeFromGridPane(currGrdPn, 0, invertedOffset);
        ImageView note = (ImageView) getNodeFromGridPane(currGrdPn, 1, invertedOffset);

        if (sheetNote.isPlayed()) {
            NotePrefix currPrefix = sheetNote.getPrefix();
            prefix.setImage(currPrefix.getImg());
            note.setImage(NOTE_SHEET_MUSIC);
        } else {
            note.setImage(null);
            prefix.setImage(null);
        }
    }

//    private int generateInvertedOffset(int colIdx, SheetNote sheetNote) {
//        assert colIdx == 0 || colIdx == 1;
//        System.out.println("todo implementation generate inverted offset");
//        return 0;
//    }

    /**
     * https://stackoverflow.com/questions/20655024/javafx-gridpane-retrieve-specific-cell-content
     *
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
