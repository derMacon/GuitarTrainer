package logic.guitar;

public class SheetNote extends Note {

    private final int offsetToLowerE;

    // todo maybe delete this cosntructor???
    public SheetNote(NoteCircle id, int octave, boolean isPlayed, int offsetToLowerE) {
        super(id, octave, isPlayed);
        this.offsetToLowerE = offsetToLowerE;
    }

    public SheetNote(int offsetToLowerE) {
        super(generateId(offsetToLowerE), generateOctave(offsetToLowerE), true);
        this.offsetToLowerE = offsetToLowerE;
    }

    private static NoteCircle generateId(int offset) {
        NoteCircle output = NoteCircle.E;
        for (int i = 0; i < offset; i++) {
            output = output.nextMajorTone();
        }
        return output;
    }

    private static int generateOctave(int offset) {
        return offset / NoteCircle.values().length;
    }

    public int getOffsetToLowerE() {
        return offsetToLowerE;
    }

    /**
     * Iterates the prefix of the current note.
     * e.g. the prefix is:
     * NEUTRAL -> SHARP
     * SHARP -> FLAT
     * FLAT -> NEUTRAL
     * <p>
     * But only if it is possible for the note (e.g. for F, C, etc. it is not possible since there is no semitone in
     * between them -> not possible to flatten)
     * <p>
     * Used to make it possible for the user to iterate through the variations with only on button.
     *
     * @return new SheetNote instance
     */
    public SheetNote iteratePrefix() {
        if (!this.isPlayed) {
            searchBasePosition();
        }
        // todo maybe offsettoLowerE is redundant ???
        NoteCircle currNoteId = this.id.nextSemiTone();
        while (!currNoteId.getNotes().containsKey(this.id.getPrimaryNote())) {
            currNoteId = currNoteId.nextSemiTone();
        }
        return new SheetNote(currNoteId, this.octave, this.isPlayed, this.offsetToLowerE);
    }

    private void searchBasePosition() {
        // todo
    }

}
