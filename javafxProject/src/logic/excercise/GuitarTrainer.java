package logic.excercise;

import logic.audio.AudioConnector;
import logic.audio.AudioConverter;
import logic.dataPreservation.Logger;
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
import java.util.List;

/**
 * Implementation of the instrument trainer interface needed for the excercise modes
 */
public class GuitarTrainer implements Trainer {

    private GUIConnector gui;
    private AudioConnector audioConv;
    private Mode mode;
    private List<Note> excercises;

    /**
     * Constructor setting up the gui and audioocnverter
     *
     * @param gui            Guiconnector needed to display the steps of the logic on the gui
     * @param audioConverter audioconverter needed to play the corresponding audio file
     */
    public GuitarTrainer(GUIConnector gui, AudioConverter audioConverter) {
        this.gui = gui;
        this.audioConv = audioConverter;
        this.excercises = new ArrayList<>();
    }

    @Override
    public void userPressedSheetNote(SheetNote sheetNote) {
        this.gui.updateSheetNotes(sheetNote);
    }

    @Override
    public void setMode(Mode mode) {
        if(ExcercisePack.contains(mode)) {
            this.mode = mode;
            gui.setReplayButtonGrayedout(this.mode == Mode.GUITAR_FREEPLAY);
            generateExerciseLst();

            for(Note note : this.excercises) {
                System.out.println(note);
            }
        }
    }

    private void generateExerciseLst() {
        assert null != this.mode;
        File file = ExcercisePack.translate(this.mode);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                this.excercises.add(NoteFactory.createNote(line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void giveExcercise() {

        Logger.getInstance().printAndSafe("Trainer gives excercise");
    }

    @Override
    public void checkResult() {
        Logger.getInstance().printAndSafe("Trainer evaluates excercise");
    }
}
