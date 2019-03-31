package logic.note;

import java.util.HashSet;
import java.util.Set;

/**
 * Class to manage the collection of possible exercises in the Trainer implementation
 */
public class ExerciseChord {

    private Set<Note> pressedNotes;

    /**
     * Default constructor
     */
    public ExerciseChord() {
        this.pressedNotes = new HashSet<>();
    }

    /**
     * Constructor
     *
     * @param pressedNotes list of pressed notes
     */
    public ExerciseChord(Set<Note> pressedNotes) {
        this.pressedNotes = pressedNotes;
    }

    public void add(Note note) {
        this.pressedNotes.add(note);
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
        for(Note curr : pressedNotes) {
            strb.append(curr.toString() + "\n");
        }
        return strb.toString();
    }

}
