package logic.excercise;

import logic.guitar.SheetNote;
import logic.organization.Mode;

public interface Trainer {
    void giveExcercise();
    void userPressedSheetNote(SheetNote sheetNote);
    void checkResult();
    void setMode(Mode mode);
}
