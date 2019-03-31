package logic.excercise;

import logic.audio.AudioConnector;
import logic.audio.AudioConverter;
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
    private AudioConnector audioConv;
    private Mode mode;
    private List<ExerciseChord> exercises;


    /**
     * Constructor setting up the gui and audioocnverter
     *
     * @param gui            Guiconnector needed to display the steps of the logic on the gui
     * @param audioConverter audioconverter needed to play the corresponding audio file
     */
    public GuitarTrainer(GUIConnector gui, AudioConverter audioConverter) {
        this.gui = gui;
        this.audioConv = audioConverter;
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

    private void generateExerciseLst() {
        assert null != this.mode;
        File file = ExcercisePack.translate(this.mode);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                this.exercises.add(NoteFactory.createChord(line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.shuffle(this.exercises);
        this.exercises = this.exercises.subList(0, POOL_SIZE - 1);
    }


    @Override
    public Note[] giveExcercise() {
        Logger.getInstance().printAndSafe(this.exercises.get(0) + " <= Trainer gives excercise");
        return this.exercises.toArray(new Note[0]);
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
