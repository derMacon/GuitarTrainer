package logic.guitar;

import gui.NotePrefix;

public abstract class Note {
    protected final Tone tone;
    protected final NotePrefix prefix;
    protected final int octave;
    protected boolean isPlayed;

    public Note(Tone tone, NotePrefix prefix, int octave, boolean isPlayed) {
        this.tone = tone;
        this.prefix = prefix;
        this.octave = octave;
        this.isPlayed = isPlayed;
    }

    public Tone getTone() {
        return tone;
    }

    public int getOctave() {
        return octave;
    }

    public NotePrefix getPrefix() {
        return prefix;
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
        return this.tone == other.tone && this.octave == other.octave
                && this.isPlayed == other.isPlayed;
    }

    @Override
    public String toString() {
        return "tone -> " + this.tone + "_" + this.prefix.name() + ", octave -> " + this.octave + ", isPlayed -> " + this.isPlayed;
    }
}
