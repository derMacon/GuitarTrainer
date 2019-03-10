package logic.guitar;

public class Note implements Comparable {
    private final NoteCircle id;
    private final Pos pos;
    private final int octave;
    private boolean isPlayed;

    /**
     * Testing constructor
     *
     * @param id  Note circle identifier
     * @param pos position on the fretboard
     */
    public Note(NoteCircle id, Pos pos) {
        this.id = id;
        this.octave = 0;
        this.pos = pos;
        this.isPlayed = true;
    }

    /**
     * Default constructor
     * @param id
     * @param octave
     * @param pos
     */
    public Note(NoteCircle id, int octave, Pos pos) {
        this.id = id;
        this.pos = pos;
        this.octave = octave;
        this.isPlayed = true;
    }

    public NoteCircle getId() {
        return id;
    }

    public Pos getPos() {
        return pos;
    }

    public int getBaseString() {
        return this.pos.getGuitarString();
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
        Pos p1 = this.getPos();
        Pos p2 = ((Note) o).getPos();
        int diff = 0;
        return diff = p1.getGuitarString() - p2.getGuitarString() == 0 ? p1.getFret() - p2.getFret() : diff;
    }

    @Override
    public boolean equals(Object o) {
        if (null == o || !(o instanceof Note)) {
            return false;
        }
        Note other = (Note) o;
        return this.id == other.id && this.octave == other.octave && this.pos.equals(other.pos);
    }

    @Override
    public String toString() {
        return this.id.name() + " -> " + this.pos.toString() + ", octave: " + this.octave
                + ", isPlayed: " + this.isPlayed;
    }


}
