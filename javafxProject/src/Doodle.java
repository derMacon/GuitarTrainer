import logic.instrument.FretboardPos;
import logic.note.FretboardNote;
import logic.note.NoteFactory;

public class Doodle {

    public static void main(String[] args) {
        FretboardNote note = NoteFactory.createFretboardNote(new FretboardPos(5, 0));
        while(note.getOctave() < 5) {
            System.out.println(note);
            note = note.nextSemiTone();
        }

    }

}
