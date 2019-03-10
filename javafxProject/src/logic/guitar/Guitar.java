package logic.guitar;

import gui.GUIConnector;

public class Guitar {
    protected final Note[] openStrings = new Note[]{
            new Note(NoteCircle.E, new Pos(0, 0)),
            new Note(NoteCircle.B, new Pos(1, 0)),
            new Note(NoteCircle.G, new Pos(2, 0)),
            new Note(NoteCircle.D, new Pos(3, 0)),
            new Note(NoteCircle.A, new Pos(4, 0)),
            new Note(NoteCircle.E, new Pos(5, 0))};
    private final int fretCnt = 13;

    protected Note[] pressedStrings;

    private GUIConnector gui;

    public Guitar(GUIConnector gui) {
        this.gui = gui;
        // init open strings
        this.pressedStrings = this.openStrings.clone();
        this.gui.initGui(this.openStrings);
    }

    public void pressNote(Pos pos) {
        assert null != pos;
        Note note = translate(pos);
        updateString(note);
    }

    public Note incOctave(Note note) throws NotOnFretException {
        Note output = findOnSameString(note);
        if (output == null) {
            output = findOnHigherString(note);
        }
        if (output == null) {
            throw new NotOnFretException("Not on fret");
        }
        return output;
    }

    private Note findOnSameString(Note note) {
        Note baseNote = this.openStrings[note.getPos().getGuitarString()];
        NoteCircle[] notes = NoteCircle.values();
        int currNoteOrd = baseNote.getId().ordinal();
        int counter = 0;
        while (notes[currNoteOrd + counter] != note.getId()) {
            counter++;
        }
        return new Note(note.getId(), new Pos(note.getPos().getGuitarString(), counter));
    }

    private Note findOnHigherString(Note note) {
        return null;
    }

    public Note decOctave(Note note) {
        // todo
        return null;
    }

    private void updateString(Note note) {
        Note oldPressed = this.pressedStrings[note.getBaseString()];
        Note newPressed = updateStrings(note);
        this.gui.updateGui(newPressed, oldPressed);


//        int idxGuitarString = note.getPos().getGuitarString();
//        // open String selected (either muted or unmuted)
//        if (note.equals(this.openStrings[idxGuitarString])) {
//            this.pressedStrings[idxGuitarString] = this.openStrings[idxGuitarString];
//            this.pressedStrings[idxGuitarString].setPlayed(!this.pressedStrings[idxGuitarString].isPlayed());
//            this.gui.setOpenStringPlayable(this.pressedStrings[idxGuitarString].isPlayed(), this
// .pressedStrings[idxGuitarString]);
//            // note previously selected (selects open String)
//        } else if (note.equals(this.pressedStrings[idxGuitarString])) {
//            this.pressedStrings[idxGuitarString] = this.openStrings[idxGuitarString];
//            this.gui.pressNote(this.openStrings[idxGuitarString]);
//            // no string previously selected
//        } else {
//            this.gui.pressNote(this.pressedStrings[idxGuitarString]);
//            this.pressedStrings[idxGuitarString] = note;
//        }
    }

    private Note updateStrings(Note note) {
        int baseGuitarString = note.getBaseString();
        Note pressedNote = this.pressedStrings[baseGuitarString];
        if (note.equals(pressedNote)) {
            pressedNote.invertPlayable();
        } else {
            this.pressedStrings[baseGuitarString] = note;
        }

        if (!this.pressedStrings[baseGuitarString].isPlayed()
                && !this.pressedStrings[baseGuitarString].equals(this.openStrings[baseGuitarString])) {
            this.pressedStrings[baseGuitarString] = this.openStrings[baseGuitarString];
        }
        return this.pressedStrings[baseGuitarString];
    }


    private Note translate(Pos pos) {
        NoteCircle[] noteCircle = NoteCircle.values();
        int ord = openStrings[pos.getGuitarString()].getId().ordinal() + pos.getFret();
        Note output = new Note(noteCircle[ord % noteCircle.length], pos);
        System.out.println("Translated: " + output);
        return output;
    }

    public void playDownStrum() {
        // todo
    }

}
