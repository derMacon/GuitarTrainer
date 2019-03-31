package logic.excercise;

import logic.note.ExerciseChord;
import logic.note.Note;
import logic.note.SheetNote;
import logic.organization.Mode;

/**
 * Interface for the trainer who gives instructions on which excercise should be played next
 */
public interface Trainer {

    /**
     * Setter for the Mode
     *
     * @param mode mode of the game
     */
    void setMode(Mode mode);

    /**
     * Generates a excercise for the user to solve with the gui
     *
     * @return note array / chord the user has to recognize
     */
    Note[] giveExcercise();

    /**
     * Method to set a new SheetNote selected by the user
     *
     * @param sheetNote SheetNote which the user selected
     */
    void userPressedSheetNote(SheetNote sheetNote);

    /**
     * Method to check the result of the user. Will be called once the user uses the check in option for the exercise
     * solution.
     *
     * @param chord chord the user checks in as a solution of the given exercise
     */
    void checkResult(ExerciseChord chord);

}
