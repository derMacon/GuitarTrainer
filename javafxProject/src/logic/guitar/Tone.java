package logic.guitar;

public enum Tone {
    C, D, E, F, G, A, B;


    /**
     * Gets the primary note of a given note.
     * E.g. D_SHARP == E_FLAT the primary note is always the one with the SHARP prefix -> in this case D
     *
     * @param offset offset from the lower E note (octave == 0)
     * @return primary noteId with the given note offset
     */
    public static Tone translateToTone(int offset) {
        return values()[(E.ordinal() + offset) % values().length];
    }

    public static int translateToOffset(Tone tone) {
        int offset = tone.ordinal() - E.ordinal();
        return offset < 0 ? offset + values().length : offset;
    }
}
