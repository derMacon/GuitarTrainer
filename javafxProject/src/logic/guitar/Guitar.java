package logic.guitar;

import logic.audio.AudioConverter;
import logic.audio.SoundPack;
import logic.dataPreservation.Logger;
import logic.organization.GUIConnector;

public class Guitar {

    protected final static Note[] OPEN_STRINGS = new Note[]{
            new Note(NoteCircle.E, 2, new FretboardPos(0, 0)),
            new Note(NoteCircle.B, 1, new FretboardPos(1, 0)),
            new Note(NoteCircle.G, 1, new FretboardPos(2, 0)),
            new Note(NoteCircle.D, 1, new FretboardPos(3, 0)),
            new Note(NoteCircle.A, 0, new FretboardPos(4, 0)),
            new Note(NoteCircle.E, 0, new FretboardPos(5, 0))};

    protected Note[] pressedStrings;

    private final GUIConnector gui;

    private final AudioConverter audioConv;

    public Guitar(GUIConnector gui, AudioConverter audioConverter) {
        this.gui = gui;
        this.pressedStrings = this.OPEN_STRINGS.clone();
        this.gui.initGui();
        this.audioConv = audioConverter;
    }

    public Guitar(GUIConnector gui) {
        this(gui, new AudioConverter(SoundPack.NYLON));
    }

    public void pressNote(FretboardPos fretboardPos) {
        assert null != fretboardPos;
        Note inputNote = translate(fretboardPos);
        Note prevNote = updateString(inputNote);
        Note currNote = this.pressedStrings[fretboardPos.getGuitarString()];
        Logger.getInstance().printAndSafe(currNote.toString());
        this.gui.updateGuitar(currNote);
        if(!inputNote.equals(prevNote)) {
            playSinglePressedNote(prevNote, currNote);
        }
    }

    protected Note translate(FretboardPos fretboardPos) {
        NoteCircle[] noteCircle = NoteCircle.values();
        int sum = OPEN_STRINGS[fretboardPos.getGuitarString()].getId().ordinal() + fretboardPos.getFret();
        int octave = (sum / noteCircle.length) + this.OPEN_STRINGS[fretboardPos.getGuitarString()].getOctave();
        int ordinalVal = sum % noteCircle.length;
        Note output = new Note(noteCircle[ordinalVal], octave, fretboardPos);
        System.out.println("Translated: " + output);
        return output;
    }

    private Note updateString(Note note) {
        Note oldPressed = this.pressedStrings[note.getBaseString()];
        updateNote(note);
        return oldPressed;
    }

    /**
     * Current selected Note will be played if
     * - the prevNote and the currNote are unequal
     * - or if the currNote is the open String and the note is actually supposed to be played
     * @param prevNote note previously selected
     * @param currNote current note selected by the user
     */
    private void playSinglePressedNote(Note prevNote, Note currNote) {
        if (!prevNote.equals(currNote)
                || currNote.equals(this.OPEN_STRINGS[currNote.getBaseString()]) && currNote.isPlayed()) {
            this.audioConv.playSingleNote(currNote);
        }
    }

    private Note findOnSameString(Note note) {
        Note baseNote = OPEN_STRINGS[note.getFretboardPos().getGuitarString()];
        NoteCircle[] notes = NoteCircle.values();
        int currNoteOrd = baseNote.getId().ordinal();
        int counter = 0;
        while (notes[currNoteOrd + counter] != note.getId()) {
            counter++;
        }
        return new Note(note.getId(), 0, new FretboardPos(note.getFretboardPos().getGuitarString(), counter));
    }

    private Note updateNote(Note note) {
        int baseGuitarString = note.getBaseString();
        Note pressedNote = this.pressedStrings[baseGuitarString];
        if (note.equals(pressedNote)) {
            pressedNote.invertPlayable();
        } else {
            this.pressedStrings[baseGuitarString] = note;
        }

        if (!pressedNote.isPlayed()
                && !pressedNote.equals(OPEN_STRINGS[baseGuitarString])) {
            this.pressedStrings[baseGuitarString] = OPEN_STRINGS[baseGuitarString];
        }
        return this.pressedStrings[baseGuitarString];
    }

    public void playDownStrum() {
        this.audioConv.playMultipleNotes(this.pressedStrings);
    }

    public void reset() {
        Note curr;
        for (int i = 0; i < this.pressedStrings.length; i++) {
            curr = this.pressedStrings[i];
            if(!curr.equals(OPEN_STRINGS[i]) || !curr.isPlayed()) {
                pressNote(new FretboardPos(i, 0));
            } else {
                this.audioConv.playSingleNote(curr);
            }
        }
    }


    // --- excercise mode ---

    private Note findOnHigherString(Note note) {
        return null;
    }

    public Note decOctave(Note note) {
        // todo
        return null;
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
}
