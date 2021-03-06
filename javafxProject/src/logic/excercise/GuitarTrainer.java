package logic.excercise;

import logic.dataPreservation.Logger;
import logic.note.ExerciseChord;
import logic.note.Note;
import logic.organization.GUIConnector;
import logic.organization.Mode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Implementation of the instrument trainer interface needed for the excercise modes
 */
public class GuitarTrainer implements Trainer {

    private static final String DESCRIPTION =
                    "Welcome to the guitar trainer. In this program you will train to distinguish different notes " +
                            "from each other and selecting their position on the guitar fretboard as well as on the " +
                            "sheet page. To achive this there are three modes implemented:\n" +
                    "- hearing a note and selecting it on the fretboard and the sheets\n" +
                    "- translating a note from one instrument to the respective counterpart\n" +
                    "- freeplay mode where the user may select any position on one instrument and see the note on the" +
                            " other instrument. \n" +
                    "\n" +
                    "The menu can be accessed by clicking on the hamburger next to the title. Here you can: \n" +
                    "- select an overall mode \n" +
                    "- open the git repository in the browser\n" +
                    "- close / end the whole program\n" +
                    "\n" +
                    "The mode can also be changed by clicking on the switch button. To leave the menu just click on " +
                            "the main window. \n" +
                    "\n" +
                    "When selecting the overall mode the specific implementation can be selected on the pagination. " +
                            "E.g. you select the hearing mode, you now can select if you want to recognize a single " +
                            "note or a whole chord. \n";

    private static final String MODE_DELIMITER = ".....................................";
    private static final String UNEQUAL_INPUT_CHORDS = "The input chords from the user don't match up. Please try " +
            "again.";
    private static final String UNEQUAL_EXP_INP_CHORD = "Both input chords are equal, but don't math the expected "
            + "exercise chord. The exercise will be shown agin in a few rounds.";
    private static final String EQUAL_EXP_INP_CHORD = "Correct! The next exercise will be played.";
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
    public void displayDescription() {
        this.gui.showMessage("", DESCRIPTION);
    }

    @Override
    public Note[] currExercise() {
        ExerciseChord chord = this.exercises.get(0);
        Logger.getInstance().printAndSafe("Current excercise:" + chord);
        return chord.toArray();
    }

    @Override
    public void setMode(Mode mode) {
        if (ExercisePack.contains(mode)) {
            this.mode = mode;
            gui.setReplayButtonGrayedout(this.mode == Mode.GUITAR_FREEPLAY);
            generateExerciseLst();
        } else {
            this.gui.setReplayButtonGrayedout(true);
        }
    }

    /**
     * Parses an appropriate text file to a list of ExerciseChords
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
    public void checkInResults(ExerciseChord guitarChord, ExerciseChord sheetChord) {
        String message;
        boolean bothInputChordsEqual = guitarChord.equals(sheetChord);

        if (bothInputChordsEqual) {
            boolean chordsEqualExercise = checkResult(guitarChord);
            if (chordsEqualExercise) {
                message = EQUAL_EXP_INP_CHORD;
            } else {
                message = UNEQUAL_EXP_INP_CHORD;
            }
        } else {
            message = UNEQUAL_INPUT_CHORDS;
        }
        this.gui.showMessage("Result checking", message);
        Logger.getInstance().printAndSafe(message);
    }

    /**
     * Checks if a given input chord generated by the user equals the expected exercise chord.
     * Also iterates the internal list of chords
     *
     * @param actChord user genereted chord
     * @return if a given input chord generated by the user equals the expected exercise chord
     */
    private boolean checkResult(ExerciseChord actChord) {
        // todo check for np exception
        ExerciseChord expChord = this.exercises.remove(0);
        boolean isSame = expChord.equals(actChord);
        if (isSame) {
            insertLst(actChord.incIterations());
        } else {
            this.exercises.add(this.wrongAnswerOffset, actChord);
        }
        Logger.getInstance().printAndSafe(expChord + " <= Expechted chord: "
                + "\n" + actChord + " <= Actual chord");
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

        // for testing purpose use this implementation of the Random class
//        Random rand = new MyRandom();

        Random rand = new Random();
        int randomNewIdx = rand.nextInt((upperBound - lowerBound) + 1) + lowerBound;
        this.exercises.add(randomNewIdx, chord);
    }

}
