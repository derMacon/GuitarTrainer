package logic.note;

/**
 * Abstract type for a Note on both guitar and sheet page. Used as the type of the generic interface 'instrument'
 */
public class Note {
    public static final String OPENING_DELIMITER = "[";
    public static final String CLOSING_DELIMITER = "]";

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
     * Constructor
     *
     * @param tone   tone of the note
     * @param prefix prefix of the note
     * @param octave octave of the note
     */
    public Note(Tone tone, Prefix prefix, int octave) {
        this(tone, prefix, octave, true);
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
     *
     * @return the isPlayed flag of the note
     */
    public boolean isPlayed() {
        return isPlayed;
    }

    /**
     * Setter for the is played flag of the note
     *
     * @param played flag determining if the note should be played or muted
     * @return a new note instance with the updated flag
     */
    public Note setPlayed(boolean played) {
        return new Note(this.tone, this.prefix, this.octave, played);
    }

    // Important: Muted flag not considered when checking for equality
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Note)) {
            return false;
        }
        Note other = (Note) o;
        return NoteCircle.getId(this.tone, this.prefix) == NoteCircle.getId(other.tone, other.prefix)
                && this.octave == other.octave;
    }

    @Override
    public String toString() {
        String template = OPENING_DELIMITER + "tone -> %9s, octave -> " + this.octave + CLOSING_DELIMITER;
        return String.format(String.format(template, this.tone + "_" + this.prefix));
    }

    @Override
    public int hashCode() {
        return NoteCircle.getId(this.tone, this.prefix).ordinal() + this.octave * NoteCircle.values().length;
    }
}
