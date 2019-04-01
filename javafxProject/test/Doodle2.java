import logic.excercise.GuitarTrainer;
import logic.excercise.Trainer;
import logic.FakeGui;
import logic.note.Note;
import logic.note.Prefix;
import logic.note.Tone;
import logic.organization.Mode;

public class Doodle2 {

    public static void main(String[] args) {
        Trainer trainer = new GuitarTrainer(new FakeGui());
        trainer.setMode(Mode.HEARING_SINGLE_NOTE);
        printNoteArr(trainer.currExercise());
        trainer.checkResult(new Note[]{new Note(Tone.E, Prefix.NEUTRAL, 0, true)});
        printNoteArr(trainer.currExercise());
        trainer.checkResult(new Note[]{new Note(Tone.F, Prefix.NEUTRAL, 0, true)});
        printNoteArr(trainer.currExercise());
        trainer.checkResult(new Note[]{new Note(Tone.F, Prefix.SHARP, 0, true)});
    }

    private static void printNoteArr(Note[] arr) {
        for (Note curr : arr) {
            System.out.println("output: " + curr);
        }
    }
}
