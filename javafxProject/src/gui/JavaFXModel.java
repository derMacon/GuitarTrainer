package gui;

import com.jfoenix.controls.JFXRadioButton;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import logic.guitar.GUIConnector;
import logic.guitar.Note;

public class JavaFXModel implements GUIConnector {


    @Override
    public void updateGui(Note currNote, Note prevNote) {
        if (!currNote.equals(prevNote)) {
            showGraphic(findBtn(prevNote), false);
            showGraphic(findBtn(currNote), true);
        }
    }

    private void showGraphic(GuitarJFXButton btn, boolean val) {
        // todo
    }

    @Override
    public void initGui(Note[] openStrings) {
        // todo
    }

    public GuitarJFXButton findBtn(Note note) {
        // todo
        return null;
    }

    @Override
    public String getNoteName() {
        return null;
    }
}
