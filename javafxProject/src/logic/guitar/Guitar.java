package logic.guitar;

import logic.audio.AudioConverter;
import logic.audio.SoundPack;
import logic.dataPreservation.Logger;
import logic.organization.GUIConnector;

public class Guitar {

    protected final static FretboardNote[] OPEN_STRINGS = new FretboardNote[]{
            new FretboardNote(NoteCircle.E, 2, new FretboardPos(0, 0)),
            new FretboardNote(NoteCircle.B, 1, new FretboardPos(1, 0)),
            new FretboardNote(NoteCircle.G, 1, new FretboardPos(2, 0)),
            new FretboardNote(NoteCircle.D, 1, new FretboardPos(3, 0)),
            new FretboardNote(NoteCircle.A, 0, new FretboardPos(4, 0)),
            new FretboardNote(NoteCircle.E, 0, new FretboardPos(5, 0))};

    protected FretboardNote[] pressedStrings;

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
        FretboardNote inputFretboardNote = translate(fretboardPos);
        FretboardNote prevFretboardNote = updateString(inputFretboardNote);
        FretboardNote currFretboardNote = this.pressedStrings[fretboardPos.getGuitarString()];
        Logger.getInstance().printAndSafe(currFretboardNote.toString());
        this.gui.updateGuitar(currFretboardNote);
        if(!inputFretboardNote.equals(prevFretboardNote)) {
            playSinglePressedNote(prevFretboardNote, currFretboardNote);
        }
    }

    protected FretboardNote translate(FretboardPos fretboardPos) {
        NoteCircle[] noteCircle = NoteCircle.values();
        int sum = OPEN_STRINGS[fretboardPos.getGuitarString()].getId().ordinal() + fretboardPos.getFret();
        int octave = (sum / noteCircle.length) + this.OPEN_STRINGS[fretboardPos.getGuitarString()].getOctave();
        int ordinalVal = sum % noteCircle.length;
        FretboardNote output = new FretboardNote(noteCircle[ordinalVal], octave, fretboardPos);
        System.out.println("Translated: " + output);
        return output;
    }

    private FretboardNote updateString(FretboardNote fretboardNote) {
        FretboardNote oldPressed = this.pressedStrings[fretboardNote.getBaseString()];
        updateNote(fretboardNote);
        return oldPressed;
    }

    /**
     * Current selected FretboardNote will be played if
     * - the prevFretboardNote and the currFretboardNote are unequal
     * - or if the currFretboardNote is the open String and the note is actually supposed to be played
     * @param prevFretboardNote note previously selected
     * @param currFretboardNote current note selected by the user
     */
    private void playSinglePressedNote(FretboardNote prevFretboardNote, FretboardNote currFretboardNote) {
        if (!prevFretboardNote.equals(currFretboardNote)
                || currFretboardNote.equals(this.OPEN_STRINGS[currFretboardNote.getBaseString()]) && currFretboardNote.isPlayed()) {
            this.audioConv.playSingleNote(currFretboardNote);
        }
    }

    private FretboardNote findOnSameString(FretboardNote fretboardNote) {
        FretboardNote baseFretboardNote = OPEN_STRINGS[fretboardNote.getFretboardPos().getGuitarString()];
        NoteCircle[] notes = NoteCircle.values();
        int currNoteOrd = baseFretboardNote.getId().ordinal();
        int counter = 0;
        while (notes[currNoteOrd + counter] != fretboardNote.getId()) {
            counter++;
        }
        return new FretboardNote(fretboardNote.getId(), 0, new FretboardPos(fretboardNote.getFretboardPos().getGuitarString(), counter));
    }

    private FretboardNote updateNote(FretboardNote fretboardNote) {
        int baseGuitarString = fretboardNote.getBaseString();
        FretboardNote pressedFretboardNote = this.pressedStrings[baseGuitarString];
        if (fretboardNote.equals(pressedFretboardNote)) {
            pressedFretboardNote.invertPlayable();
        } else {
            this.pressedStrings[baseGuitarString] = fretboardNote;
        }

        if (!pressedFretboardNote.isPlayed()
                && !pressedFretboardNote.equals(OPEN_STRINGS[baseGuitarString])) {
            this.pressedStrings[baseGuitarString] = OPEN_STRINGS[baseGuitarString];
        }
        return this.pressedStrings[baseGuitarString];
    }

    public void playDownStrum() {
        this.audioConv.playMultipleNotes(this.pressedStrings);
    }

    public void reset() {
        FretboardNote curr;
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

    private FretboardNote findOnHigherString(FretboardNote fretboardNote) {
        return null;
    }

    public FretboardNote decOctave(FretboardNote fretboardNote) {
        // todo
        return null;
    }

    public FretboardNote incOctave(FretboardNote fretboardNote) throws NotOnFretException {
        FretboardNote output = findOnSameString(fretboardNote);
        if (output == null) {
            output = findOnHigherString(fretboardNote);
        }
        if (output == null) {
            throw new NotOnFretException("Not on fret");
        }
        return output;
    }
}
