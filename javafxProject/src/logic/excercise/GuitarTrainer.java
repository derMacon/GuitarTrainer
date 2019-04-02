package logic.excercise;

import logic.MyRandom;
import logic.dataPreservation.Logger;
import logic.note.ExerciseChord;
import logic.note.Note;
import logic.note.SheetNote;
import logic.organization.GUIConnector;
import logic.organization.Mode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Implementation of the instrument trainer interface needed for the excercise modes
 */
public class GuitarTrainer implements Trainer {

    private static final int DEFAULT_WRONG_ANSWER_OFFSET = 3;
    private static final int DEFAULT_POOL_SIZE = 10;
    private static final String COMMENT_PREFIX = "//";

    private GUIConnector gui;
    private Mode mode;
    private List<ExerciseChord> exercises;

    private int wrongAnswerOffset;

    /**
     * Constructor setting up the gui and audioocnverter
     *
     * @param gui Guiconnector needed to display the steps of the logic on the gui
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
        return this.exercises.get(0).toArray();
    }

    @Override
    public Note[] nextExercise() {
        System.out.println("todo next exercise guitar trainer");
        Logger.getInstance().printAndSafe(this.exercises.get(0) + " <= Trainer gives excercise");

        return this.exercises.get(0).toArray();
    }

    /**
     * Parses an appropriate text file to an list of ExerciseChords
     */
    private void generateExerciseLst() {
        assert null != this.mode;
        this.exercises = Parser.parseExercise(this.mode, DEFAULT_POOL_SIZE);
        if (this.exercises.size() > DEFAULT_POOL_SIZE) {
            this.exercises = this.exercises.subList(0, DEFAULT_POOL_SIZE - 1);
        }
        this.wrongAnswerOffset = this.exercises.size() > DEFAULT_WRONG_ANSWER_OFFSET
                ? DEFAULT_WRONG_ANSWER_OFFSET : this.exercises.size() - 1;
    }


    @Override
    public boolean checkResult(Note[] notes) {
        // todo check for np exception
        ExerciseChord expChord = this.exercises.remove(0);
        ExerciseChord actChord = new ExerciseChord(notes);
        boolean isSame = expChord.equals(actChord);
        if (isSame) {
            insertLst(actChord.incIterations());
        } else {
            this.exercises.add(this.wrongAnswerOffset, actChord);
        }
        Logger.getInstance().printAndSafe(expChord + " <= Expechted chord: "
                + "\n" + actChord + " <= Actual chord");

        // todo delte this - only for testing
        for (ExerciseChord curr : this.exercises) {
            System.out.println(curr + " <= only for testing purpose");
        }
        System.out.println();

        return isSame;
    }

    /**
     * Inserts the chord to the appropriate spot in the list. The iteration count is the main concern at this point.
     *
     * @param chord chord that will be inserted into the list
     */
    private void insertLst(ExerciseChord chord) {
        int lstSize = this.exercises.size();
        int lowerBound = lstSize;
        int upperBound = lstSize;
        int inputIterations = chord.getIterations();
        int currIterations;
        for (int i = 0; i < lstSize; i++) {
            currIterations = this.exercises.get(i).getIterations();
            if (inputIterations == currIterations) {
                lowerBound = i;
            } else if (inputIterations == currIterations - 1) {
                upperBound = i - 1;
            }
        }
        Random rand = new MyRandom();
        System.out.println(lowerBound + " " + upperBound);
        int randomNewIdx = rand.nextInt((upperBound - lowerBound) + 1) + lowerBound;
        System.out.println(randomNewIdx);
        this.exercises.add(randomNewIdx, chord);
    }

}
