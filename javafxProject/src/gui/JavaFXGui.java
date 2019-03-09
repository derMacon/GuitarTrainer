package gui;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import logic.guitar.Pos;

public class JavaFXGui implements GUIConnector {

    private GridPane pressedNotes;

    public JavaFXGui(GridPane pressedNotes) {
        this.pressedNotes = pressedNotes;
    }

    @Override
    public void pressNote(Pos pos) {
        boolean noteFound = false;
        String id = pos.getGuitarString() + "" + pos.getFret();
        ObservableList<Node> notes = this.pressedNotes.getChildren();
        for (int i = 0; i < notes.size(); i++) {
            noteFound = notes.get(i).getId().equals(id);
            if (noteFound) {
                notes.get(i).fireEvent(new ActionEvent());
            }
        }

    }

    @Override
    public void getNoteName() {

    }
}
