package logic.guitar;

public class Note implements Comparable {
    private final NoteCircle id;
    private final FretboardPos fretboardPos;
    private final int octave;
    private boolean isPlayed;

    /**
     * Testing constructor
     *
     * @param id  Note circle identifier
     * @param fretboardPos position on the fretboard
     */
    public Note(NoteCircle id, FretboardPos fretboardPos) {
        this.id = id;
        this.octave = 0;
        this.fretboardPos = fretboardPos;
        this.isPlayed = true;
    }

    /**
     * Default constructor
     * @param id
     * @param octave
     * @param fretboardPos
     */
    public Note(NoteCircle id, int octave, FretboardPos fretboardPos) {
        this.id = id;
        this.fretboardPos = fretboardPos;
        this.octave = octave;
        this.isPlayed = true;
    }

    public NoteCircle getId() {
        return id;
    }

    public FretboardPos getFretboardPos() {
        return fretboardPos;
    }

    public int getBaseString() {
        return this.fretboardPos.getGuitarString();
    }

    public int getOctave() {
        return octave;
    }

    public boolean isPlayed() {
        return isPlayed;
    }

    public void invertPlayable() {
        this.isPlayed = !this.isPlayed;
    }

    @Override
    public int compareTo(Object o) {
        assert null != o;
        assert o instanceof Note;
        FretboardPos p1 = this.getFretboardPos();
        FretboardPos p2 = ((Note) o).getFretboardPos();
        int diff = 0;
        return diff = p1.getGuitarString() - p2.getGuitarString() == 0 ? p1.getFret() - p2.getFret() : diff;
    }

    @Override
    public boolean equals(Object o) {
        if (null == o || !(o instanceof Note)) {
            return false;
        }
        Note other = (Note) o;
        return this.id == other.id && this.octave == other.octave && this.fretboardPos.equals(other.fretboardPos);
    }

    @Override
    public String toString() {
        return this.id.name() + " -> " + this.fretboardPos.toString() + ", octave: " + this.octave
                + ", isPlayed: " + this.isPlayed;
    }


}
