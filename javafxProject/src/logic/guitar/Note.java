package logic.guitar;

public abstract class Note {
    protected final NoteCircle id;
    protected final int octave;
    protected boolean isPlayed;

    public Note(NoteCircle id, int octave, boolean isPlayed) {
        this.id = id;
        this.octave = octave;
        this.isPlayed = isPlayed;
    }

    public NoteCircle getId() {
        return id;
    }

    public int getOctave() {
        return octave;
    }

    public boolean isPlayed() {
        return isPlayed;
    }

    public void setPlayed(boolean played) {
        isPlayed = played;
    }

    public void invertPlayable() {
        this.isPlayed = !this.isPlayed;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Note)) {
            return false;
        }
        Note other = (Note) o;
        return this.id == other.id && this.octave == other.octave
                && this.isPlayed == other.isPlayed;
    }

    @Override
    public String toString() {
        return "id -> " + this.id + ", octave -> " + this.octave + ", isPlayed -> " + this.isPlayed;
    }
}
