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
//        ((GuitarJFXButton)this.panes[0].getChildren().get(2)).pressNote(true);
//        findBtn(currNote).invertGraphic();
//        findBtn(prevNote).invertGraphic();

        GuitarJFXButton prev = findBtn(prevNote);
        GuitarJFXButton curr = findBtn(currNote);


//        findBtn(currNote).invertGraphic();
//        if (!currNote.equals(prevNote)) {
//            findBtn(prevNote).invertGraphic();
//        }

        curr.pressNote(true);
        prev.pressNote(true);

//        if(!prevNote.equals(currNote)) {
//            prev.pressNote(false);
//        }


//
//        if (!currNote.equals(prevNote)) {
//            findBtn(prevNote).pressNote(false);
//            findBtn(currNote).pressNote(true);
//        } else {
//            findBtn(prevNote).pressNote(true);
//            findBtn(currNote).pressNote(false);
//
////            findBtn(currNote).invertGraphic();
//        }
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
