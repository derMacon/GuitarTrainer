package logic.guitar;

public class FretboardNote extends Note implements Comparable {
    private final FretboardPos fretboardPos;

    /**
     * Default constructor
     * @param id
     * @param octave
     * @param fretboardPos
     */
    public FretboardNote(NoteCircle id, int octave, FretboardPos fretboardPos) {
        super(id, octave, true);
        this.fretboardPos = fretboardPos;
    }

    public FretboardPos getFretboardPos() {
        return fretboardPos;
    }

    public int getBaseString() {
        return this.fretboardPos.getGuitarString();
    }

    @Override
    public int compareTo(Object o) {
        assert null != o;
        assert o instanceof FretboardNote;
        FretboardPos p1 = this.getFretboardPos();
        FretboardPos p2 = ((FretboardNote) o).getFretboardPos();
        int diff = 0;
        return diff = p1.getGuitarString() - p2.getGuitarString() == 0 ? p1.getFret() - p2.getFret() : diff;
    }

    @Override
    public boolean equals(Object o) {
        if (null == o || !(o instanceof FretboardNote)) {
            return false;
        }
        FretboardNote other = (FretboardNote) o;
        return this.id == other.id && this.octave == other.octave && this.fretboardPos.equals(other.fretboardPos);
    }

    @Override
    public String toString() {
        return this.id.name() + " -> " + this.fretboardPos.toString() + ", octave: " + this.octave
                + ", isPlayed: " + this.isPlayed;
    }


}
