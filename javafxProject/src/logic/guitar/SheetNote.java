package logic.guitar;

/**
 * Class implementing bahavior of a sheet note. In contrast to a guitar note this note does not have a fret position
 * and also is able to iterate it's prefix.
 */
public class SheetNote extends Note {


    public SheetNote(NoteCircle id, int octave, boolean isPlayed) {
        super(id, octave, isPlayed);
    }

    public SheetNote(int offsetToLowerE) {
        super(generateId(offsetToLowerE), generateOctave(offsetToLowerE), true);
    }

    private static NoteCircle generateId(int offset) {
        NoteCircle output = NoteCircle.E;
        for (int i = 0; i < offset; i++) {
            output = output.nextMajorTone();
        }
        return output;
    }

    private static int generateOctave(int offset) {
        return (offset + Tone.E.ordinal()) / Tone.values().length;
    }

    public int getOffsetToLowestE() {
        SheetNote lowestENote = new SheetNote(0);
        int counter = 0;
        while(this.id.getPrimaryTone() != lowestENote.getId().getPrimaryTone() || this.octave != lowestENote.getOctave()) {
            lowestENote = lowestENote.nextMajorTone();
            counter++;
        }
        return counter;
    }

    public SheetNote getLowestNoteOfTone() {
        Tone baseTone = this.id.getTones().get(0);
        NoteCircle outputId = NoteCircle.values()[0];
        while (!outputId.getTones().contains(baseTone)) {
            outputId = NoteCircle.values()[outputId.ordinal() + 1];
        }
        return new SheetNote(outputId, this.octave, this.isPlayed);
    }

    public SheetNote nextSemiTone() {
        NoteCircle note = this.getId().nextSemiTone();
        return note == NoteCircle.values()[0] ?
                new SheetNote(note, this.octave + 1, this.isPlayed)
                : new SheetNote(note, this.octave, this.isPlayed);
    }

    public SheetNote nextMajorTone() {
        NoteCircle note = this.getId().nextMajorTone();
        return note == NoteCircle.values()[0] ?
                new SheetNote(note, this.octave + 1, this.isPlayed)
                : new SheetNote(note, this.octave, this.isPlayed);
    }


}
