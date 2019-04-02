package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import logic.note.FretboardNote;
import logic.note.Prefix;
import logic.note.SheetNote;
import logic.organization.GUIConnector;

/**
 * Implementation of the interface needed for the logic to display its content on the gui
 */
public class JavaFXGui implements GUIConnector {

    public static final String[] PREFIX_IMG_PATH = new String[]{
            "sheetNotes\\prefix_flat.png",
            "sheetNotes\\prefix_neutral.png",
            "sheetNotes\\prefix_sharp.png"
    };

    private static final int NOTES_ONTOP_LINES = 0;
    private static final int NOTES_BETWEEN_LINES = 1;
    private static final int SELECTED_PIXEL = 20;
    private static final Image NOTE_SHEET_MUSIC = new Image("sheetNotes\\note.png", SELECTED_PIXEL + 20,
            SELECTED_PIXEL, true, true);
    /**
     * Gridpane array of one dimensional gridpanes representing the frets of the users instrument on the gui. Index
     * mirrors
     * fret num
     */
    private final GridPane[] frets;

    /**
     * array containing two gridpanes, on for the sheet notes between and one for the notes on top
     * of the lines on the sheet page
     */
    private final GridPane[] sheetNotes;

    /**
     * Replay button for the excercise
     */
    private final JFXButton replayBtn;

    private StackPane popUp;

    /**
     * Constructor setting up all necessary gui components
     *
     * @param fretboard  array of gridpanes each representing one fret of the fretboard from the guitar
     * @param sheetNotes array containing two gridpanes, on for the sheet notes between and one for the notes on top
     *                   of the lines on the sheet page
     * @param replayBtn  replay button for the excercise
     */
    public JavaFXGui(GridPane[] fretboard, GridPane[] sheetNotes, JFXButton replayBtn, StackPane popUp) {
        this.frets = fretboard;
        this.sheetNotes = sheetNotes;
        this.replayBtn = replayBtn;
        this.popUp = popUp;
    }

    @Override
    public void updateGuitar(FretboardNote currFretboardNote) {
        GuitarJFXButton currBtn = null;
        for (int idxFret = 0; idxFret < this.frets.length; idxFret++) {
            currBtn = (GuitarJFXButton) this.frets[idxFret].getChildren().get(currFretboardNote.getBaseString());
            currBtn.selectButton(currFretboardNote.isPlayed()
                    && currFretboardNote.getFretboardPos().getFret() == idxFret);
        }
    }

    @Override
    public void updateSheetNotes(SheetNote sheetNote) {
        GridPane currGrdPn = sheetNote.getOffsetToLowestE() % 2 == 0
                ? this.sheetNotes[NOTES_BETWEEN_LINES] : this.sheetNotes[NOTES_ONTOP_LINES];
        int spaceCnt = currGrdPn.getRowConstraints().size();
        int invertedOffset = spaceCnt - 1 - (sheetNote.getOffsetToLowestE() / 2);
        ImageView prefix = (ImageView) getNodeFromGridPane(currGrdPn, 0, invertedOffset);
        ImageView note = (ImageView) getNodeFromGridPane(currGrdPn, 1, invertedOffset);

//        note.setTranslateX(20);

        if (sheetNote.isPlayed()) {
            Prefix currPrefix = sheetNote.getPrefix();

//            PrefixImages displayedImage = PrefixImages.values()[currPrefix.ordinal()];
//            if (prefix.getImage() != null) {
//                displayedImage = displayedImage.add(PrefixImages.getId(prefix.getImage().));
//            }

            prefix.setImage(new Image(PREFIX_IMG_PATH[currPrefix.ordinal()], SELECTED_PIXEL, SELECTED_PIXEL, true,
                    true));
            note.setImage(NOTE_SHEET_MUSIC);
        } else {
            note.setImage(null);
            prefix.setImage(null);
        }
    }

    /**
     * https://stackoverflow.com/questions/20655024/javafx-gridpane-retrieve-specific-cell-content
     * Returns the node at the given column / row
     *
     * @param gridPane gridpane from which the node should be returned
     * @param col      column index of the node
     * @param row      row index of the node
     * @return the node at the given column / row
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
    /**
     * Opens a dialog window with the given title and message
     * @param title title of the dialog window
     * @param message message of the dialog window
     */
    private void showMessage(String title, String message) {
    StackPane stackpane = this.popUp;

        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text(title));
        TextFlow flow = new TextFlow(new Text(message));
        flow.setTextAlignment(TextAlignment.JUSTIFY);
        content.setBody(flow);
        content.setMaxWidth(stackpane.getWidth() * 0.9);

        stackpane.setDisable(false);
        JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton button = new JFXButton("Okay");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
                stackpane.setDisable(true);
            }
        });

        button.setButtonType(com.jfoenix.controls.JFXButton.ButtonType.RAISED);
        button.setPrefHeight(32);
        content.setActions(button);
        dialog.show();
    }
}
