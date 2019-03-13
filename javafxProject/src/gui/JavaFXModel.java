package gui;

import com.jfoenix.controls.JFXButton;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import logic.guitar.GUIConnector;
import logic.guitar.Note;

public class JavaFXModel implements GUIConnector {

    private final GridPane[] panes;

    public JavaFXModel(GridPane[] panes) {
        this.panes = panes;
    }

    @Override
    public void updateGui(Note currNote, Note prevNote) {
        GuitarJFXButton prev = findBtn(prevNote);
        GuitarJFXButton curr = findBtn(currNote);

        prev.invertGraphic();
        if(!prevNote.equals(currNote)) {
            curr.invertGraphic();
        }
    }

    @Override
    public void initGui(Note[] openStrings) {
        // todo delete args
        for(Node curr : panes[0].getChildren()) {
            assert curr instanceof GuitarJFXButton;
            ((GuitarJFXButton) curr).pressNote(true);
        }
    }

    public GuitarJFXButton findBtn(Note note) {
        int idxBaseString = note.getBaseString();
        int idxFret = note.getPos().getFret();
        System.out.println(note + "Finding Button at: str -> " + idxBaseString + ", Fret -> " + idxFret);
        return (GuitarJFXButton) this.panes[idxFret].getChildren().get(idxBaseString);
    }

    @Override
    public String getNoteName() {
        return null;
    }
}
