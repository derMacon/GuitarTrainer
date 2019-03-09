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
//        ObservableList<Node> lst = this.pressedNotes.getChildren();
//        for(Node curr : lst) {
//            System.out.println(curr);
//        }



        boolean noteFound = false;
        String id = GuitarTrainingDocumentController.RADIO_BTN_PREFIX + pos.getGuitarString() + pos.getFret();
        ObservableList<Node> notes = this.pressedNotes.getChildren();
        for (int i = 0; i < notes.size() && !noteFound; i++) {
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
