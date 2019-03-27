package gui;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.GlyphIcon;
import de.jensd.fx.glyphs.GlyphsBuilder;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;
import logic.audio.AudioConverter;
import logic.instrument.FretboardPos;
import logic.organization.FlowOrganizer;
import logic.organization.Mode;
import logic.organization.Organized;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller of the gui
 */
public class GuitarFretboardController implements Initializable {

    private static final String FRETBOARD_TEXUTURE_PATH = "textures\\guitarGui4_smallHeight.png";
    private static final String STICKY_NOTE_RIGHT_TEXTURE_PATH = "textures\\paper.png";
    private static final String STICKY_NOTE_LEFT_TEXTURE_PATH = "textures\\paper3.png";
    private static final String CLEF_TEXTURE_PATH = "sheetNotes\\background_withHelpingLines.png";
    @FXML
    private GridPane grdPn_fret0;
    @FXML
    private GridPane grdPn_fret1;
    @FXML
    private GridPane grdPn_fret2;
    @FXML
    private ImageView imgBase;
    @FXML
    private AnchorPane imgParent;
    @FXML
    private GridPane grdPn_fret17;
    @FXML
    private GridPane grdPn_fret16;
    @FXML
    private GridPane grdPn_fret18;
    @FXML
    private GridPane grdPn_fret9;
    @FXML
    private GridPane grdPn_fret7;
    @FXML
    private GridPane grdPn_fret11;
    @FXML
    private GridPane grdPn_fret8;
    @FXML
    private GridPane grdPn_fret10;
    @FXML
    private GridPane grdPn_fret19;
    @FXML
    private GridPane grdPn_fret5;
    @FXML
    private GridPane grdPn_fret13;
    @FXML
    private GridPane grdPn_fret6;
    @FXML
    private GridPane grdPn_fret12;
    @FXML
    private GridPane grdPn_fret3;
    @FXML
    private GridPane grdPn_fret15;
    @FXML
    private GridPane grdPn_fret4;
    @FXML
    private GridPane grdPn_fret14;
    @FXML
    private GridPane grpPn_Buttons;
    @FXML
    private JFXButton btn_replay;
    @FXML
    private JFXButton btn_strum;
    @FXML
    private JFXButton btn_checkIn;
    @FXML
    private JFXButton btn_reset;
    @FXML
    private ImageView img_sticky_right;
    @FXML
    private ImageView img_sticky_left;
    @FXML
    private Pagination pgn_modes;
    @FXML
    private ImageView imgVw_clefTexture;
    @FXML
    private MenuItem mnTm_github;
    @FXML
    private MenuItem mnTm_close;
    @FXML
    private MenuItem mnTm_info;
    @FXML
    private AnchorPane nchrPn_sheetImg;
    @FXML
    private GridPane grdPn_sheetNotes_betweenLines;
    @FXML
    private GridPane grdPn_sheetNotes_onLines;
    @FXML
    private GridPane grdPn_totalSumNotes;
    @FXML
    private JFXButton btn_sheet_00;
    @FXML
    private JFXButton btn_sheet_01;
    @FXML
    private JFXButton btn_sheet_02;
    @FXML
    private JFXButton btn_sheet_03;
    @FXML
    private JFXButton btn_sheet_04;
    @FXML
    private JFXButton btn_sheet_05;
    @FXML
    private JFXButton btn_sheet_06;
    @FXML
    private JFXButton btn_sheet_07;
    @FXML
    private JFXButton btn_sheet_08;
    @FXML
    private JFXButton btn_sheet_09;
    @FXML
    private JFXButton btn_sheet_10;
    @FXML
    private JFXButton btn_sheet_11;
    @FXML
    private JFXButton btn_sheet_12;
    @FXML
    private JFXButton btn_sheet_13;
    @FXML
    private JFXButton btn_sheet_14;
    @FXML
    private JFXButton btn_sheet_15;
    @FXML
    private JFXButton btn_sheet_16;
    @FXML
    private JFXButton btn_sheet_17;
    @FXML
    private JFXButton btn_sheet_18;
    @FXML
    private JFXButton btn_sheet_19;
    @FXML
    private JFXButton btn_sheet_20;
    @FXML
    private JFXButton btn_sheet_21;
    @FXML
    private JFXButton btn_sheet_22;
    @FXML
    private JFXButton btn_sheet_23;
    private Organized flowOrganizer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initMainButtons(this.btn_replay, FontAwesomeIcon.UNDO);
        initMainButtons(this.btn_strum, FontAwesomeIcon.MUSIC);
        initMainButtons(this.btn_checkIn, FontAwesomeIcon.CERTIFICATE);

        initResetButton();

        initMenu(this.mnTm_github, FontAwesomeIcon.GITHUB);
        initMenu(this.mnTm_close, FontAwesomeIcon.EXCLAMATION_TRIANGLE);
        initMenu(this.mnTm_info, FontAwesomeIcon.INFO_CIRCLE);

        initGuitarTexture();
        initNotePadTexture();
        initClefTexture();
        initFrets();
        initSheet();
        initDescription();

        GridPane[] frets = new GridPane[]{grdPn_fret0, grdPn_fret1, grdPn_fret2, grdPn_fret3, grdPn_fret4,
                grdPn_fret5, grdPn_fret6, grdPn_fret7, grdPn_fret8, grdPn_fret9, grdPn_fret10, grdPn_fret11,
                grdPn_fret12, grdPn_fret13, grdPn_fret14, grdPn_fret15, grdPn_fret16, grdPn_fret17, grdPn_fret18,
                grdPn_fret19};

        GridPane[] sheetNotes = new GridPane[]{this.grdPn_sheetNotes_onLines, this.grdPn_sheetNotes_betweenLines};

        this.flowOrganizer = new FlowOrganizer(new JavaFXGui(frets, sheetNotes, this.btn_replay),
                new AudioConverter(), Mode.values()[this.pgn_modes.getCurrentPageIndex()]);
    }


    // --- initializing gui with textures / buttons ---

    private void initMainButtons(JFXButton btn, FontAwesomeIcon icon) {
        btn.setStyle(
                "-fx-background-color: #d6e1fc;\n"
                        + "-fx-font-family: \"Forte\";\n"
                        + "-fx-graphic-text-gap: 15;\n"
                        + "-fx-font-size: 35;");
        btn.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        btn.setButtonType(JFXButton.ButtonType.RAISED);
        btn.setRipplerFill(Paint.valueOf("#ffffff"));
        GlyphIcon glypIcon = GlyphsBuilder.create(FontAwesomeIconView.class)
                .glyph(icon)
                .build();
        glypIcon.setSize("4em");
        btn.setGraphic(glypIcon);
    }

    /**
     * Initializes the reset button
     */
    private void initResetButton() {
        this.btn_reset.setStyle(
                "-fx-background-color: transparent;\n"
                        + "-fx-font-family: \"Forte\";\n"
                        + "-fx-graphic-text-gap: 15;\n"
                        + "-fx-font-size: 28;");
        this.btn_reset.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        this.btn_reset.setButtonType(JFXButton.ButtonType.FLAT);
        this.btn_reset.setRipplerFill(Paint.valueOf("#ffffff"));
        GlyphIcon glypIcon = GlyphsBuilder.create(FontAwesomeIconView.class)
                .glyph(FontAwesomeIcon.TRASH_ALT)
                .build();
        glypIcon.setSize("3em");
        this.btn_reset.setGraphic(glypIcon);
    }

    /**
     * Initializes the instrument fret texture
     */
    private void initGuitarTexture() {
        double parentWidth = this.imgParent.getBoundsInParent().getWidth();
        double parentHeight = this.imgParent.getBoundsInParent().getHeight();
//        this.imgParent.setMinHeight(parentHeight);
//        this.imgParent.setMinWidth(parentWidth);
        this.imgBase.setImage(new Image(FRETBOARD_TEXUTURE_PATH, parentWidth, parentHeight, true, true));
        // https://stackoverflow.com/questions/12630296/resizing-images-to-fit-the-parent-node
        this.imgBase.fitWidthProperty().bind(this.imgParent.widthProperty());
        this.imgBase.fitHeightProperty().bind(this.imgParent.heightProperty());
        this.imgBase.setY(this.imgBase.getParent().getTranslateY());
    }

    /**
     * Initializes the note pad texture
     */
    private void initNotePadTexture() {
        this.img_sticky_right.setImage(new Image(STICKY_NOTE_RIGHT_TEXTURE_PATH));
        this.img_sticky_left.setImage(new Image(STICKY_NOTE_LEFT_TEXTURE_PATH));
    }

    /**
     * Inits the givin menu item with the a given icon
     * @param mnItem menu item to initialize
     * @param icon icon that will be shown on the menu item
     */
    private void initMenu(MenuItem mnItem, FontAwesomeIcon icon) {
        GlyphIcon glypIcon = GlyphsBuilder.create(FontAwesomeIconView.class)
                .glyph(icon)
                .build();
        glypIcon.setSize("1em");
        mnItem.setGraphic(glypIcon);
    }

    /**
     * Initializes the clef texture
     */
    private void initClefTexture() {
        this.imgVw_clefTexture.setImage(new Image(CLEF_TEXTURE_PATH));
    }

    /**
     * Loads up all transparent buttons on the fretboard
     */
    private void initFrets() {
        int guitarStringCnt = 6;
        for (int i = 0; i < guitarStringCnt; i++) {
            this.grdPn_fret0.add(createGuitarButton(i, 0), 0, i);
            this.grdPn_fret1.add(createGuitarButton(i, 1), 0, i);
            this.grdPn_fret2.add(createGuitarButton(i, 2), 0, i);
            this.grdPn_fret3.add(createGuitarButton(i, 3), 0, i);
            this.grdPn_fret4.add(createGuitarButton(i, 4), 0, i);
            this.grdPn_fret5.add(createGuitarButton(i, 5), 0, i);
            this.grdPn_fret6.add(createGuitarButton(i, 6), 0, i);
            this.grdPn_fret7.add(createGuitarButton(i, 7), 0, i);
            this.grdPn_fret8.add(createGuitarButton(i, 8), 0, i);
            this.grdPn_fret9.add(createGuitarButton(i, 9), 0, i);
            this.grdPn_fret10.add(createGuitarButton(i, 10), 0, i);
            this.grdPn_fret11.add(createGuitarButton(i, 11), 0, i);
            this.grdPn_fret12.add(createGuitarButton(i, 12), 0, i);
            this.grdPn_fret13.add(createGuitarButton(i, 13), 0, i);
            this.grdPn_fret14.add(createGuitarButton(i, 14), 0, i);
            this.grdPn_fret15.add(createGuitarButton(i, 15), 0, i);
            this.grdPn_fret16.add(createGuitarButton(i, 16), 0, i);
            this.grdPn_fret17.add(createGuitarButton(i, 17), 0, i);
            this.grdPn_fret18.add(createGuitarButton(i, 18), 0, i);
            this.grdPn_fret19.add(createGuitarButton(i, 19), 0, i);
        }
    }

    /**
     * Initializes sheet gridpanes
     */
    private void initSheet() {
        // inits images in Gridpanes
        for (int i = 0; i < 12; i++) {
            this.grdPn_sheetNotes_betweenLines.add(new ImageView(), 0, i);
            this.grdPn_sheetNotes_betweenLines.add(new ImageView(), 1, i);

            this.grdPn_sheetNotes_onLines.add(new ImageView(), 0, i);
            this.grdPn_sheetNotes_onLines.add(new ImageView(), 1, i);
        }
    }

    /**
     * Initializes the pagination
     */
    private void initDescription() {
        this.pgn_modes.setPageCount(Mode.values().length);
        this.pgn_modes.setPageFactory((Integer pageIdx) -> changeMode(pageIdx));
    }

    /**
     * Page factory for the pagination
     * @param idx index of the page
     * @return page for the given index
     */
    private Label changeMode(Integer idx) {
        this.flowOrganizer.interpretMode(Mode.values()[idx]);
        Label label = new Label(Mode.values()[idx].getDescr());
        label.setTextAlignment(TextAlignment.CENTER);
        return label;
    }

    // todo maybe delete this button
    private SheetNoteJFXButton createSheetNoteButton(int noteOffset) {
        SheetNoteJFXButton button = new SheetNoteJFXButton(noteOffset);
//        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        button.setScaleY(0.7);
        button.setOnAction(e -> sheetNoteButtonPressed(button));
        return button;
    }

    private void sheetNoteButtonPressed(SheetNoteJFXButton btn) {
//        btn.invertGraphic();
        this.flowOrganizer.sheetNotePressed(btn.getLineOffset());
    }

    @FXML
    public void sheetNoteBtnPressed(ActionEvent event) {
        int offset = Integer.parseInt(((JFXButton) event.getSource()).getId().split("_")[2]);
        this.flowOrganizer.sheetNotePressed(offset);
    }

    /**
     * Creates a button with given string and fret position
     *
     * @param guitarString instrument string on which the button will be located
     * @param fret         fret on which the button will be located
     * @return a button with given string and fret position
     */
    private GuitarJFXButton createGuitarButton(int guitarString, int fret) {
        GuitarJFXButton button = new GuitarJFXButton(guitarString, fret);
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        button.setOnAction(e -> guitarButtonPressed(button));
        return button;
    }

    /**
     * presses a note on the instrument attribut of this class
     *
     * @param button Button that is being pressed by the user
     */
    private void guitarButtonPressed(GuitarJFXButton button) {
        this.flowOrganizer.pressNoteOnFretboard(new FretboardPos(button.getGuitarString(), button.getGuitarFret()));
    }

    /**
     * Plays a down strum with the selected notes
     */
    @FXML
    public void playDownStrum() {
        this.flowOrganizer.playDownStrum();
    }

    /**
     * shuts down the whole program
     *
     * @param event Actionevent called by the user
     */
    @FXML
    void endProgramm(ActionEvent event) {
        System.exit(0);
    }

    /**
     * Opens git repository in browser
     *
     * @param event Actionevent called by the user
     */
    @FXML
    void openRepo(ActionEvent event) {
        openWebpage("https://github.com/derMacon/GuitarTrainer");
    }

    /**
     * Opens a given URL in the users default browser
     *
     * @param url url to open in the browser
     */
    private void openWebpage(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            System.out.println("Could not open git repo");
        }
    }

    @FXML
    private void reset(ActionEvent event) {
        this.flowOrganizer.reset();
    }

    @FXML
    private void replayExcercise(ActionEvent event) {
        System.out.println("Replay btn pressed [FretContr. l. 386]");
    }


}
