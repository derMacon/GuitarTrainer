package logic.guitar;

import gui.GUIConnector;
import logic.audio.AudioConverter;

public class Guitar {
    protected final Note[] openStrings = new Note[]{
            new Note(NoteCircle.E, 2, new Pos(0, 0)),
            new Note(NoteCircle.B, 1, new Pos(1, 0)),
            new Note(NoteCircle.G, 1, new Pos(2, 0)),
            new Note(NoteCircle.D, 1,  new Pos(3, 0)),
            new Note(NoteCircle.A, 0, new Pos(4, 0)),
            new Note(NoteCircle.E, 0,  new Pos(5, 0))};
    private final int fretCnt = 13;

    protected Note[] pressedStrings;

    private final GUIConnector gui;

    private final AudioConverter audioConv;

    public Guitar(GUIConnector gui, AudioConverter audioConverter) {
        this.gui = gui;
        this.pressedStrings = this.openStrings.clone();
        this.gui.initGui(this.openStrings);
        this.audioConv = audioConverter;
    }

    public Guitar(GUIConnector gui) {
        this(gui, new AudioConverter("res\\audioFiles"));
    }

    public void pressNote(Pos pos) {
        assert null != pos;
        Note note = translate(pos);
        updateString(note);
        audioConv.playSingleNote(note);
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
        return new Note(note.getId(), 0, new Pos(note.getPos().getGuitarString(), counter));
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
    }

    private Note updateStrings(Note note) {
        int baseGuitarString = note.getBaseString();
        Note pressedNote = this.pressedStrings[baseGuitarString];
        if (note.equals(pressedNote)) {
            pressedNote.invertPlayable();
        } else {
            this.pressedStrings[baseGuitarString] = note;
        }

        if (!pressedNote.isPlayed()
                && !pressedNote.equals(this.openStrings[baseGuitarString])) {
            this.pressedStrings[baseGuitarString] = this.openStrings[baseGuitarString];
        }
        return this.pressedStrings[baseGuitarString];
    }


    protected Note translate(Pos pos) {
        NoteCircle[] noteCircle = NoteCircle.values();
        int sum = openStrings[pos.getGuitarString()].getId().ordinal() + pos.getFret();
        int octave = (sum / noteCircle.length) + this.openStrings[pos.getGuitarString()].getOctave();
        int ordinalVal = sum % noteCircle.length;
        Note output = new Note(noteCircle[ordinalVal], octave, pos);
        System.out.println("Translated: " + output);
        return output;
    }

    public void playDownStrum() {
        // todo
    }

}
