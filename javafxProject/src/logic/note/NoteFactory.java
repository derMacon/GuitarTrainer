package logic.note;

import logic.instrument.FretboardNote;
import logic.instrument.FretboardPos;
import logic.instrument.Guitar;

public class NoteFactory {

    /**
     * Translates a given position to a note
     *
     * @param pos position of the note on the fretboard
     * @return note at the specified position
     */
    public static FretboardNote createFretboardNote(FretboardPos pos) {
        FretboardNote note = Guitar.OPEN_STRINGS[pos.getGuitarString()];
        for (int i = 0; i < pos.getFret(); i++) {
            note = note.nextSemiTone();
        }
        return note;
    }

    public static SheetNote createSheetNote(int offset) {
        Tone tone = Tone.values()[(Tone.E.ordinal() + offset) % Tone.values().length];
        Prefix prefix = Prefix.NEUTRAL;
        int octave = (offset + Tone.E.ordinal()) / Tone.values().length;
        boolean isPlayed = true;

        return new SheetNote(tone, prefix, octave, isPlayed);
    }

    public static SheetNote createSheetNote(FretboardNote note) {
        return new SheetNote(note.getTone(), note.getPrefix(), note.getOctave(), note.isPlayed());
    }

}
