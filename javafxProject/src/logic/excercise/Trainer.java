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
     * Getter for the current exercise
     *
     * @return note array / chord the user has to recognize
     */
    Note[] currExercise();

    /**
     * Generates a new chord the user has to recognize
     *
     * @return note array / chord the user has to recognize
     */
    Note[] nextExercise();

    /**
     * Method to set a new SheetNote selected by the user
     *
     * @param sheetNote SheetNote which the user selected
     */
    void userPressedSheetNote(SheetNote sheetNote);

    /**
     * Checks both input chord from the gui
     *
     * @param fretboardChord chord generated from the fretboard
     * @param sheetChord     chord generated from the sheet page
     */
    void checkInResults(ExerciseChord fretboardChord, ExerciseChord sheetChord);

}
