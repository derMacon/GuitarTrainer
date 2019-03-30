package logic.note;

import java.util.HashSet;
import java.util.Set;

public class Chord {

    private Set<Note> pressedNotes;

    public Chord() {
        this.pressedNotes = new HashSet<>();
    }

    public void add(Note note) {
        this.pressedNotes.add(note);
    }

    @Override
    public boolean equals(Object o) {
        if(o == null || !(o instanceof Chord)) {
            return false;
        }
        Chord other = (Chord) o;

        Set<Note> temp = new HashSet<>(this.pressedNotes);
        temp.addAll(other.pressedNotes);

        return this.pressedNotes.size() == other.pressedNotes.size()
                && temp.size() == this.pressedNotes.size();
    }

}
