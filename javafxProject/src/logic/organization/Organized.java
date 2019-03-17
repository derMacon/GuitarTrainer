package logic.organization;

import logic.guitar.FretboardPos;

public interface Organized {
    void playExcercise();
    void checkInResult();
    void playDownStrum();
    void pressNoteOnFretboard(FretboardPos fretboardPos);
}
