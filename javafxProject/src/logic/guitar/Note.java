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

    public void invertPlayable() {
        this.isPlayed = !this.isPlayed;
    }

}
