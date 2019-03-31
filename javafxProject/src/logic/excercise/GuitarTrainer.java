package logic.excercise;

import logic.dataPreservation.Logger;
import logic.note.ExerciseChord;
import logic.note.Note;
import logic.note.NoteFactory;
import logic.note.SheetNote;
import logic.organization.GUIConnector;
import logic.organization.Mode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of the instrument trainer interface needed for the excercise modes
 */
public class GuitarTrainer implements Trainer {

    private static final int WRONG_ANSWER_OFFSET = 10;
    private static final int POOL_SIZE = 10;

    private GUIConnector gui;
    private Mode mode;
    private List<ExerciseChord> exercises;


    /**
     * Constructor setting up the gui and audioocnverter
     *  @param gui            Guiconnector needed to display the steps of the logic on the gui
     */
    public GuitarTrainer(GUIConnector gui) {
        this.gui = gui;
        this.exercises = new ArrayList<>();
    }

    @Override
    public void userPressedSheetNote(SheetNote sheetNote) {
        this.gui.updateSheetNotes(sheetNote);
    }

    @Override
    public void setMode(Mode mode) {
        if (ExcercisePack.contains(mode)) {
            this.mode = mode;
            gui.setReplayButtonGrayedout(this.mode == Mode.GUITAR_FREEPLAY);
            generateExerciseLst();
        }
    }

    @Override
    public Note[] currExercise() {
        Logger.getInstance().printAndSafe(this.exercises.get(0) + " <= Trainer gives excercise");
        return this.exercises.get(0).toArray();
    }

    @Override
    public Note[] nextExercise() {
        System.out.println("todo next exercise guitar trainer");
        return this.exercises.get(0).toArray();
    }

    private void generateExerciseLst() {
        assert null != this.mode;
        File file = ExcercisePack.translate(this.mode);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            ExerciseChord currChord = new ExerciseChord();
            while ((line = br.readLine()) != null) {
                // text file must start with something other than a blank line
                if(line.length() == 0) {
                    this.exercises.add(currChord);
                    currChord = new ExerciseChord();
                } else {
                    currChord.add(NoteFactory.createNote(line));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(this.exercises.size() > 1) {
            Collections.shuffle(this.exercises);
        }
        this.exercises = this.exercises.subList(0, POOL_SIZE - 1);
    }


    @Override
    public void checkResult(ExerciseChord chord) {
        // todo check for np exception
        if (this.exercises.get(0).equals(chord)) {
            insertLst(chord);
        } else {
            this.exercises.add(WRONG_ANSWER_OFFSET, chord);
        }
    }

    /**
     * Inserts the chord to the appropriate spot in the list. The iteration count is the main concern at this point,
     *
     * @param chord chord that will be inserted into the list
     */
    private void insertLst(ExerciseChord chord) {
        System.out.println("todo");
    }
}
