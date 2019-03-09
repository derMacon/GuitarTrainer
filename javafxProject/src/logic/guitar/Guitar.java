package logic.guitar;

import gui.GUIConnector;

public class Guitar {
    private final Note[] openStrings = new Note[]{
            new Note(NoteCircle.E, new Pos(0, 0)),
            new Note(NoteCircle.B, new Pos(1, 0)),
            new Note(NoteCircle.G, new Pos(2, 0)),
            new Note(NoteCircle.D, new Pos(3, 0)),
            new Note(NoteCircle.A, new Pos(4, 0)),
            new Note(NoteCircle.E, new Pos(5, 0))};

    private Note[] pressedStrings;

    private GUIConnector gui;

    public Guitar(GUIConnector gui) {
        this.gui = gui;
        // init open strings
        this.pressedStrings = this.openStrings.clone();
        for(Note curr : this.pressedStrings) {
            this.gui.pressNote(curr.getPos());
        }
    }

    public void pressNote(Pos pos) {
        assert null != pos;
        Note note = translate(pos);
        updateString(note);

//        this.gui.pressNote(this.pressedStrings[pos.getGuitarString()]);
    }

    private void updateString(Note note) {
        int idxGuitarString = note.getPos().getGuitarString();

        // open String selected (either muted or unmuted)
        if (note.equals(this.openStrings[idxGuitarString])) {
            this.pressedStrings[idxGuitarString] = this.openStrings[idxGuitarString];
            this.pressedStrings[idxGuitarString].setPlayed(!this.pressedStrings[idxGuitarString].isPlayed());
            // note previously selected (selects open String)
        } else if (note.equals(this.pressedStrings[idxGuitarString])) {
            this.pressedStrings[idxGuitarString] = this.openStrings[idxGuitarString];
//            this.gui.pressNote(note.getPos());
            this.gui.pressNote(this.openStrings[idxGuitarString].getPos());
            // no string previously selected
        } else {
            this.gui.pressNote(this.pressedStrings[idxGuitarString].getPos());
            this.pressedStrings[idxGuitarString] = note;
        }
    }

    private Note translate(Pos pos) {
        NoteCircle[] noteCircle = NoteCircle.values();
        int ord = noteCircle[pos.getGuitarString()].ordinal() + pos.getFret();
        return new Note(noteCircle[ord % noteCircle.length], pos);
    }

    public void playDownStrum() {
        // todo
    }

}
