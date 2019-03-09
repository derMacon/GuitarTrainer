package logic.guitar;

import gui.GUIConnector;

import java.util.Arrays;
import java.util.List;

public class Guitar {
    private final Note[] openStrings = new Note[]{
                new Note(NoteCircle.E, new Pos(0,0)),
                new Note(NoteCircle.B, new Pos(1, 0)),
                new Note(NoteCircle.G, new Pos(2, 0)),
                new Note(NoteCircle.D, new Pos(3, 0)),
                new Note(NoteCircle.A, new Pos(4, 0)),
                new Note(NoteCircle.E, new Pos(5, 0))};

    private List<Note> pressedStrings;

    private GUIConnector gui;

    public Guitar(GUIConnector gui) {
        this.gui = gui;
        this.pressedStrings = Arrays.asList(this.openStrings);
    }

    public void pressNote(Pos pos) {
        assert null != pos;
        Note note = translate(pos);
        clearGuitarString(pos.getGuitarString());
        this.pressedStrings.add(note);

    }

    private void clearGuitarString(int stringOrd) {
        for(Note note : this.pressedStrings) {
            if(note.getPos().getGuitarString() == stringOrd) {
                this.pressedStrings.remove(note);
                this.pressedStrings.add(this.openStrings[stringOrd]);
            }
        }
    }

    private Note translate(Pos pos) {
        NoteCircle[] noteCircle = NoteCircle.values();
        int ord = noteCircle[pos.getGuitarString()].ordinal() + pos.getFret();
        return new Note(noteCircle[ord % noteCircle.length], pos);
    }

    public void releaseNote(Pos pos) {
        assert null != pos;
        this.pressedStrings.remove(translate(pos));
    }

    public void playDownStrum() {
        // todo
    }

}
