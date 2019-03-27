package logic.excercise;

import logic.note.SheetNote;
import logic.organization.Mode;

public interface Trainer {
    void giveExcercise();

    void userPressedSheetNote(SheetNote sheetNote);

    void checkResult();

    void setMode(Mode mode);
}
