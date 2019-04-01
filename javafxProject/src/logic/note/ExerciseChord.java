package logic.note;

import logic.excercise.ExcerciseNote;

import java.util.HashSet;
import java.util.Set;

/**
 * Class to manage the collection of possible exercises in the Trainer implementation
 */
public class ExerciseChord {

    private Set<Note> pressedNotes;
    private int iterations;

    /**
     * Default constructor
     */
    public ExerciseChord() {
        this.pressedNotes = new HashSet<>();
        this.iterations = 0;
    }

    public ExerciseChord(Note... notes) {
        this.pressedNotes = new HashSet<>();
        for (Note curr : notes) {
            add(curr);
        }
        this.iterations = 0;
    }

    public ExerciseChord incIterations() {
        this.iterations++;
        return this;
    }

    public int getIterations() {
        return iterations;
    }

    public void add(Note note) {
        if (note.isPlayed) {
            this.pressedNotes.add(note);
        }
    }

    public Note[] toArray() {
        return this.pressedNotes.toArray(new Note[0]);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof ExerciseChord)) {
            return false;
        }
        ExerciseChord other = (ExerciseChord) o;

        Set<Note> temp = new HashSet<>(this.pressedNotes);
        temp.addAll(other.pressedNotes);

        return this.pressedNotes.size() == other.pressedNotes.size()
                && temp.size() == this.pressedNotes.size();
    }

    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        for (Note curr : pressedNotes) {
            strb.append(curr.toString());
        }
        return strb.length() == 0 ? "[]" : strb.toString();
    }

}
