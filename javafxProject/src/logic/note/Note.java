package logic.note;

/**
 * Abstract type for a Note on both guitar and sheet page. Used as the type of the generic interface 'instrument'
 */
public abstract class Note {
    protected final Tone tone;
    protected final Prefix prefix;
    protected final int octave;
    protected final boolean isPlayed;

    /**
     * Constructor
     *
     * @param tone     tone of the note
     * @param prefix   prefix of the note (flat / sharp / neutral)
     * @param octave   octave of the note
     * @param isPlayed flag determining if the note is played or muted
     */
    public Note(Tone tone, Prefix prefix, int octave, boolean isPlayed) {
        this.tone = tone;
        this.prefix = prefix;
        this.octave = octave;
        this.isPlayed = isPlayed;
    }

    /**
     * Getter for the tone
     *
     * @return tone of the note
     */
    public Tone getTone() {
        return tone;
    }

    /**
     * getter for the octave of the note
     *
     * @return octave of the note
     */
    public int getOctave() {
        return octave;
    }

    /**
     * Getter for the prefix of the note
     *
     * @return prefix of the note
     */
    public Prefix getPrefix() {
        return prefix;
    }

    /**
     * Getter for the isPlayed flag of the note
     * @return the isPlayed flag of the note
     */
    public boolean isPlayed() {
        return isPlayed;
    }

    /**
     * Setter for the is played flag of the note
     * @param played flag determining if the note should be played or muted
     */
    public abstract Note setPlayed(boolean played);

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Note)) {
            return false;
        }
        Note other = (Note) o;
        return this.tone == other.tone && this.prefix == other.prefix
                && this.octave == other.octave && this.isPlayed == other.isPlayed;
    }

    @Override
    public String toString() {
        return "tone -> " + this.tone + "_" + this.prefix.name() + ", octave -> " + this.octave + ", isPlayed -> "
                + this.isPlayed;
    }

}
