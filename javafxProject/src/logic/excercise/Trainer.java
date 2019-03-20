package logic.excercise;

import logic.guitar.Guitar;
import logic.guitar.Note;
import logic.organization.Mode;

import java.util.List;

public interface Trainer {
    void giveExcercise();
    void checkResult();
    void setMode(Mode mode);
}
