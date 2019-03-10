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
        if (!currNote.equals(prevNote)) {
            findBtn(prevNote).setSelected(false);
            findBtn(currNote).setSelected(true);
        }
    }

    @Override
    public void initGui(Note[] openStrings) {
        for (Note curr : openStrings) {
            findBtn(curr).setSelected(true);
        }
    }

    public JFXRadioButton findBtn(Note note) {
        JFXRadioButton output = null;
        String id =
                GuitarTrainingDocumentController.RADIO_BTN_PREFIX + note.getPos().getGuitarString() + note.getPos().getFret();
        ObservableList<Node> notes = this.pressedNotes.getChildren();
        for (int i = 0; i < notes.size() && output == null; i++) {
            if (notes.get(i).getId().equals(id)) {
                output = (JFXRadioButton) notes.get(i);
            }
        }
        return output;
    }

    @Override
    public String getNoteName() {
        return null;
    }
}
