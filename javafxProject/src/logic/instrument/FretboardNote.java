package logic.instrument;

import logic.note.Prefix;
import logic.note.Note;
import logic.note.NoteCircle;
import logic.note.Tone;

import java.io.PrintWriter;

/**
 * Note on the fret board of a the instrument
 */
public class FretboardNote extends Note implements Comparable {

    /**
     * Position of the note on the fret board
     */
    private final FretboardPos fretboardPos;

    /**
     * Default constructor
     *
     * @param id           note id of the note in the note circle
     * @param octave       octave of the note
     * @param fretboardPos position on the fretboard
     */
    public FretboardNote(NoteCircle id, int octave, FretboardPos fretboardPos) {
        // todo delete this constructor
//        super(id, octave, true);
        super(null, null, 0, true);
        this.fretboardPos = fretboardPos;
    }

    /**
     * Constructor setting all attribute
     * @param tone tone to set
     * @param prefix prefix of the note
     * @param octave octave of the note
     * @param isPlayed flag to state if note is muted or not
     * @param fretboardPos fretboard position of the note
     */
    public FretboardNote(Tone tone, Prefix prefix, int octave, boolean isPlayed, FretboardPos fretboardPos) {
        super(tone, prefix, octave, isPlayed);
        this.fretboardPos = fretboardPos;
    }

    /**
     * Getter for the fretboard position
     *
     * @return the fretboard position
     */
    public FretboardPos getFretboardPos() {
        return fretboardPos;
    }

    /**
     * Getter for the base string position of the note
     *
     * @return the base string position of the note
     */
    public int getBaseString() {
        return this.fretboardPos.getGuitarString();
    }


    public FretboardNote nextSemiTone() {
        int this_noteOrd = NoteCircle.getId(tone, prefix).ordinal();

        NoteCircle next_id = NoteCircle.values()[(this_noteOrd + 1) % NoteCircle.values().length];
        Tone next_tone = next_id.getPrimaryTone();
        Prefix next_prefix = next_id.getNotes().get(next_tone);
        int next_octave = next_tone.ordinal() == 0 && next_prefix == Prefix.NEUTRAL ? this.octave + 1 : this.octave;
        boolean next_isPlayed = this.isPlayed;

        return new FretboardNote(next_tone, next_prefix, next_octave, next_isPlayed, this.fretboardPos.incFret());
    }


    @Override
    public int compareTo(Object o) {
        assert null != o;
        assert o instanceof FretboardNote;
        FretboardPos p1 = this.getFretboardPos();
        FretboardPos p2 = ((FretboardNote) o).getFretboardPos();
        int diff = p1.getGuitarString() - p2.getGuitarString();
        return diff == 0 ? p1.getFret() - p2.getFret() : diff;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (null == o || !(o instanceof FretboardNote)) {
//            return false;
//        }
//        FretboardNote other = (FretboardNote) o;
//        return this.id == other.id && this.octave == other.octave && this.fretboardPos.equals(other.fretboardPos);
//    }
//
//    @Override
//    public String toString() {
//        return this.id.name() + " -> " + this.fretboardPos.toString() + ", octave: " + this.octave
//                + ", isPlayed: " + this.isPlayed;
//    }

}
