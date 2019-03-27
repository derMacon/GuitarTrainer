package logic.audio;

import logic.note.Note;

/**
 * Interface necessary to play audio files
 */
public interface AudioConnector {

    /**
     * Plays single given note
     *
     * @param note note to play
     */
    void playSingleNote(Note note);


    /**
     * Plays multiple notes of a given array at once
     *
     * @param notes array of notes to play
     */
    void playMultipleNotes(Note[] notes);
}
