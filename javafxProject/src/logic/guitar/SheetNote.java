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

    public int getOffsetToLowerE() {
        return offsetToLowerE;
    }

    private static NoteCircle generateId(int offset) {
        return NoteCircle.values()[offset % NoteCircle.values().length];
    }

    private static int generateOctave(int offset) {
        return offset / NoteCircle.values().length;
    }
}
