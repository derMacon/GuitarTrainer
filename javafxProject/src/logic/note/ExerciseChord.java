package logic.note;

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

    /**
     * Constructor
     *
     * @param notes notes of the chord
     */
    public ExerciseChord(Note... notes) {
        this.pressedNotes = new HashSet<>();
        for (Note curr : notes) {
            add(curr);
        }
        this.iterations = 0;
    }

    /**
     * Increments the iteration count of the chord
     *
     * @return the instance with an incremented iteration count
     */
    public ExerciseChord incIterations() {
        this.iterations++;
        return this;
    }

    /**
     * Getter for the iteration
     *
     * @return number of times the chord was already played in the trainer
     */
    public int getIterations() {
        return iterations;
    }

    /**
     * Adds another note to the chord
     *
     * @param note note to add to the chords
     */
    public void add(Note note) {
        if (note.isPlayed) {
            this.pressedNotes.add(note);
        }
    }

    /**
     * Generates an array out of the chord to display / play on the top level class
     *
     * @return an array out of the chord to display / play on the top level class
     */
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
