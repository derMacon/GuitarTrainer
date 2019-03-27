package logic.note;

import logic.instrument.FretboardNote;
import logic.instrument.FretboardPos;
import logic.instrument.Guitar;

import java.util.ArrayList;
import java.util.List;

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

    public static List<FretboardNote> createFretboardNote(SheetNote note) {
        List<FretboardNote> output = new ArrayList<>();
        FretboardNote currNote;
        for(FretboardNote currOpenString : Guitar.OPEN_STRINGS) {
            currNote = currOpenString;
            for (int i = 0; i < Guitar.FRET_CNT - 1; i++) {
                if(note.equals(currNote)) {
                    output.add(currNote);
                }
                currNote = currNote.nextSemiTone();
            }
        }
        return output;
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
