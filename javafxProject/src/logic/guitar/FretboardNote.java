package logic.guitar;

/**
 * Note on the fret board of a the guitar
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
//        super(id, octave, true);
        super(null, null, 0, true);
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
