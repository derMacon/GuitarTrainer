package logic.instrument;

import logic.note.NoteFactory;
import logic.note.Prefix;
import logic.audio.AudioConnector;
import logic.audio.AudioConverter;
import logic.audio.SoundPack;
import logic.dataPreservation.Logger;
import logic.note.Tone;
import logic.organization.GUIConnector;

/**
 * Class implementing a instrument with the ability to play notes / chords
 */
public class Guitar implements Instrument<FretboardNote> {

    public static final FretboardNote[] OPEN_STRINGS = new FretboardNote[]{
            new FretboardNote(Tone.E, Prefix.NEUTRAL, 2, true, new FretboardPos(0, 0)),
            new FretboardNote(Tone.B, Prefix.NEUTRAL, 1, true, new FretboardPos(1, 0)),
            new FretboardNote(Tone.G, Prefix.NEUTRAL, 1, true, new FretboardPos(2, 0)),
            new FretboardNote(Tone.D, Prefix.NEUTRAL, 1, true, new FretboardPos(3, 0)),
            new FretboardNote(Tone.A, Prefix.NEUTRAL, 0, true, new FretboardPos(4, 0)),
            new FretboardNote(Tone.E, Prefix.NEUTRAL, 0, true, new FretboardPos(5, 0))};

    public static final int FRET_CNT = 20;
    public static final int GUITAR_STRING_CNT = 6;


    protected FretboardNote[] pressedStrings;
    private final GUIConnector gui;
    private final AudioConnector audioConv;

    /**
     * Constructor setting gui and the audio converter component
     *
     * @param gui            GuiConnector component connecting logic to the gui
     * @param audioConverter audio converter to make it possible to play notes from the instrument
     */
    public Guitar(GUIConnector gui, AudioConverter audioConverter) {
        this.gui = gui;
        this.pressedStrings = this.OPEN_STRINGS.clone();
        this.gui.initGui();
        this.audioConv = audioConverter;
    }

    /**
     * Default constructor
     *
     * @param gui GuiConnector component connecting logic to the gui
     */
    public Guitar(GUIConnector gui) {
        this(gui, new AudioConverter(SoundPack.NYLON));
    }

    @Override
    public FretboardNote[] getPressedNotes() {
        return this.pressedStrings;
    }

    /**
     * Plays a down strum of all previously selected notes on the instrument fretboard
     */
    @Override
    public void playStrum() {
        this.audioConv.playMultipleNotes(this.pressedStrings);
    }

    /**
     * Resets all pressed notes on the instrument
     */
    @Override
    public void reset() {
        FretboardNote curr;
        for (int i = 0; i < this.pressedStrings.length; i++) {
            curr = this.pressedStrings[i];
            if (!curr.equals(OPEN_STRINGS[i]) || !curr.isPlayed()) {
                updateNotes(NoteFactory.createFretboardNote(new FretboardPos(i, 0)));
            }
        }
    }

    @Override
    public void pressNote(FretboardNote note) {
        FretboardNote prevNote = updateNotes(note);
        if (!note.equals(prevNote)) {
            playSinglePressedNote(prevNote, this.pressedStrings[note.getBaseString()]);
        }
    }

    /**
     * Presses a note on the instrument
     *
     * @param inputFretboardNote note to be pressed
     */
    protected FretboardNote updateNotes(FretboardNote inputFretboardNote) {
        assert inputFretboardNote != null;
        FretboardNote prevFretboardNote = updateString(inputFretboardNote);
        FretboardNote currFretboardNote = this.pressedStrings[inputFretboardNote.getBaseString()];
        this.gui.updateGuitar(currFretboardNote);
        return prevFretboardNote;
    }

    /**
     * Updates a given guitaar string
     *
     * @param fretboardNote note on the instrument neck from which the base string will be updated
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
        System.out.println(prevFretboardNote + " " + currFretboardNote);
        if (!prevFretboardNote.equals(currFretboardNote)
                || currFretboardNote.equals(this.OPEN_STRINGS[currFretboardNote.getBaseString()])
                && currFretboardNote.isPlayed()) {
            this.audioConv.playSingleNote(currFretboardNote);
        } else {
            this.audioConv.playSingleNote(prevFretboardNote);
        }
    }

    /**
     * Updates the pressed notes of the instance according to the given input note.
     * When the input note is already in the set of previously selected notes the same notes play status will be
     * inverted (muted, etc.)
     *
     * @param fretboardNote note the user selected on the instrument
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

}
