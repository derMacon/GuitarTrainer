package logic.excercise;

import logic.note.Note;
import logic.note.Prefix;
import logic.note.Tone;

public class ExcerciseNote extends Note {

    private final int iterationCnt;

    public ExcerciseNote(Tone tone, Prefix prefix, int octave, boolean isPlayed) {
        super(tone, prefix, octave, isPlayed);
        this.iterationCnt = 0;
    }

    public ExcerciseNote(Tone tone, Prefix prefix, int octave, boolean isPlayed, int iterationCnt) {
        super(tone, prefix, octave, isPlayed);
        this.iterationCnt = iterationCnt;
    }

    public ExcerciseNote incIterationsCnt() {
        return new ExcerciseNote(this.tone, this.prefix, this.octave, this.isPlayed, this.iterationCnt + 1);
    }


}
