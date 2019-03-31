package logic.note;

import java.util.HashSet;
import java.util.Set;

/**
 * Class to manage the collection of possible exercises in the Trainer implementation
 */
public class ExcerciseChord {

    private Set<Note> pressedNotes;

    /**
     * Default constructor
     */
    public ExcerciseChord() {
        this.pressedNotes = new HashSet<>();
    }

    public void add(Note note) {
        this.pressedNotes.add(note);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof ExcerciseChord)) {
            return false;
        }
        ExcerciseChord other = (ExcerciseChord) o;

        Set<Note> temp = new HashSet<>(this.pressedNotes);
        temp.addAll(other.pressedNotes);

        return this.pressedNotes.size() == other.pressedNotes.size()
                && temp.size() == this.pressedNotes.size();
    }

}
