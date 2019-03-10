package gui;

import com.jfoenix.controls.JFXRadioButton;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import logic.guitar.Note;

public class JavaFXGui implements GUIConnector {

    private GridPane pressedNotes;

    public JavaFXGui(GridPane pressedNotes) {
        this.pressedNotes = pressedNotes;
    }

    @Override
    public void updateGui(Note currNote, Note prevNote) {
        // only for open strings possible due to the radio buttons
        if (!currNote.equals(prevNote)) {
            pressNote(prevNote);
            pressNote(currNote);
        }
    }

    @Override
    public void initGui(Note[] openStrings) {
        for(Note curr : openStrings) {
            pressNote(curr);
        }
    }

    public void pressNote(Note note) {
//        ObservableList<Node> lst = this.pressedNotes.getChildren();
//        for(Node curr : lst) {
//            System.out.println(curr);
//        }

        boolean noteFound = false;
        String id =
                GuitarTrainingDocumentController.RADIO_BTN_PREFIX + note.getPos().getGuitarString() + note.getPos().getFret();
        ObservableList<Node> notes = this.pressedNotes.getChildren();
        for (int i = 0; i < notes.size() && !noteFound; i++) {
            noteFound = notes.get(i).getId().equals(id);
            if (noteFound) {
                JFXRadioButton currBtn = (JFXRadioButton) notes.get(i);
                currBtn.setSelected(!currBtn.isSelected());
            }
        }
    }

    @Override
    public String getNoteName() {
        return null;
    }
}
