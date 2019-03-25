package logic.guitar;

import logic.audio.AudioConnector;
import logic.audio.AudioConverter;
import logic.audio.SoundPack;
import logic.dataPreservation.Logger;
import logic.organization.GUIConnector;

/**
 * Class implementing a guitar with the ability to play notes / chords
 */
public class Guitar {

    protected static final FretboardNote[] OPEN_STRINGS = new FretboardNote[]{
            new FretboardNote(NoteCircle.E, 2, new FretboardPos(0, 0)),
            new FretboardNote(NoteCircle.B, 1, new FretboardPos(1, 0)),
            new FretboardNote(NoteCircle.G, 1, new FretboardPos(2, 0)),
            new FretboardNote(NoteCircle.D, 1, new FretboardPos(3, 0)),
            new FretboardNote(NoteCircle.A, 0, new FretboardPos(4, 0)),
            new FretboardNote(NoteCircle.E, 0, new FretboardPos(5, 0))};

    protected FretboardNote[] pressedStrings;
    private final GUIConnector gui;
    private final AudioConnector audioConv;

    /**
     * Constructor setting gui and the audio converter component
     * @param gui GuiConnector component connecting logic to the gui
     * @param audioConverter audio converter to make it possible to play notes from the guitar
     */
    public Guitar(GUIConnector gui, AudioConverter audioConverter) {
        this.gui = gui;
        this.pressedStrings = this.OPEN_STRINGS.clone();
        this.gui.initGui();
        this.audioConv = audioConverter;
    }

    /**
     * Default constructor
     * @param gui GuiConnector component connecting logic to the gui
     */
    public Guitar(GUIConnector gui) {
        this(gui, new AudioConverter(SoundPack.NYLON));
    }

    /**
     * Presses a note on the guitar
     * @param fretboardPos note to be pressed
     */
    public void pressNote(FretboardPos fretboardPos) {
        assert null != fretboardPos;
        FretboardNote inputFretboardNote = translate(fretboardPos);
        FretboardNote prevFretboardNote = updateString(inputFretboardNote);
        FretboardNote currFretboardNote = this.pressedStrings[fretboardPos.getGuitarString()];
        Logger.getInstance().printAndSafe(currFretboardNote.toString());
        this.gui.updateGuitar(currFretboardNote);
        if (!inputFretboardNote.equals(prevFretboardNote)) {
            playSinglePressedNote(prevFretboardNote, currFretboardNote);
        }
    }

    /**
     * Translates a given position to a note
     * @param fretboardPos position of the note
     * @return note at the specified position
     */
    protected FretboardNote translate(FretboardPos fretboardPos) {
//        NoteCircle[] noteCircle = NoteCircle.values();
//        int sum = OPEN_STRINGS[fretboardPos.getGuitarString()].getId().ordinal() + fretboardPos.getFret();
//        int octave = (sum / noteCircle.length) + this.OPEN_STRINGS[fretboardPos.getGuitarString()].getOctave();
//        int ordinalVal = sum % noteCircle.length;
//        FretboardNote output = new FretboardNote(noteCircle[ordinalVal], octave, fretboardPos);
//        System.out.println("Translated: " + output);
//        return output;

        System.out.println("todo guitar - translate");

        return null;
    }

    /**
     * Updates a given guitaar string
     * @param fretboardNote note on the guitar neck from which the base string will be updated
     * @return the previously selected note from the input note's base string
     */
    private FretboardNote updateString(FretboardNote fretboardNote) {
        FretboardNote oldPressed = this.pressedStrings[fretboardNote.getBaseString()];
        updateNote(fretboardNote);
        return oldPressed;
    }

    /**
     * Current selected FretboardNote will be played if
     * - the prevFretboardNote and the currFretboardNote are unequal
     * - or if the currFretboardNote is the open String and the note is actually supposed to be played
     *
     * @param prevFretboardNote note previously selected
     * @param currFretboardNote current note selected by the user
     */
    private void playSinglePressedNote(FretboardNote prevFretboardNote, FretboardNote currFretboardNote) {
        if (!prevFretboardNote.equals(currFretboardNote)
                || currFretboardNote.equals(this.OPEN_STRINGS[currFretboardNote.getBaseString()])
                && currFretboardNote.isPlayed()) {
            this.audioConv.playSingleNote(currFretboardNote);
        }
    }

    /**
     * Updates the pressed notes of the instance according to the given input note.
     * When the input note is already in the set of previously selected notes the same notes play status will be
     * inverted (muted, etc.)
     * @param fretboardNote note the user selected on the guitar
     * @return the newly pressed / updated note
     */
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

    /**
     * Plays a down strum of all previously selected notes on the guitar fretboard
     */
    public void playStrum() {
        this.audioConv.playMultipleNotes(this.pressedStrings);
    }

    /**
     * Resets all pressed notes on the guitar
     */
    public void reset() {
        FretboardNote curr;
        for (int i = 0; i < this.pressedStrings.length; i++) {
            curr = this.pressedStrings[i];
            if (!curr.equals(OPEN_STRINGS[i]) || !curr.isPlayed()) {
                pressNote(new FretboardPos(i, 0));
            } else {
                this.audioConv.playSingleNote(curr);
            }
        }
    }

}
