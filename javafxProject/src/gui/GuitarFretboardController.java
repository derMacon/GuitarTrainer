package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import de.jensd.fx.glyphs.GlyphIcon;
import de.jensd.fx.glyphs.GlyphsBuilder;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import logic.audio.AudioConverter;
import logic.instrument.FretboardPos;
import logic.instrument.Guitar;
import logic.organization.Category;
import logic.organization.FlowOrganizer;
import logic.organization.GUIConnector;
import logic.organization.Mode;
import logic.organization.Organized;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

//import logic.audio.AudioConverter;

/**
 * Controller of the gui
 */
public class GuitarFretboardController implements Initializable {

    private static final String FRETBOARD_TEXUTURE_PATH = "/textures/guitarGui4_smallHeight.png";
    private static final String STICKY_NOTE_RIGHT_TEXTURE_PATH = "/textures/paper.png";
    private static final String STICKY_NOTE_LEFT_TEXTURE_PATH = "/textures/paper3.png";
    private static final String CLEF_TEXTURE_PATH = "/sheetNotes/background_withHelpingLines.png";
    private static final int MAIN_BTN_FONT_SIZE = 35;
    private static final int MAIN_ICON_FONT_SIZE = 4;


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
    private JFXButton btn_replay;
    @FXML
    private JFXButton btn_strum;
    @FXML
    private JFXButton btn_checkIn;
    @FXML
    private JFXButton btn_reset;
    @FXML
    private JFXButton btn_switch;
    @FXML
    private ImageView img_sticky_right;
    @FXML
    private ImageView img_sticky_left;
    @FXML
    private Pagination pgn_modes;
    @FXML
    private ImageView imgVw_clefTexture;
    @FXML
    private GridPane grdPn_sheetNotes_betweenLines;
    @FXML
    private GridPane grdPn_sheetNotes_onLines;
    @FXML
    private StackPane stPn_popUp;
    @FXML
    private JFXDrawer drw_mainMenu;
    @FXML
    private JFXDrawer drw_modeImplementations;

    private Category selectedModeCategory;

    private Organized flowOrganizer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.selectedModeCategory = Category.FREEPLAY;

        initMainButtons(this.btn_replay, FontAwesomeIcon.UNDO);
        initMainButtons(this.btn_strum, FontAwesomeIcon.MUSIC);
        initMainButtons(this.btn_checkIn, FontAwesomeIcon.CERTIFICATE);

        initSwitchModeButton(this.btn_reset, FontAwesomeIcon.TRASH_ALT);
        initSwitchModeButton(this.btn_switch, FontAwesomeIcon.REFRESH);

        initMainDrawer(this.drw_mainMenu);
        initModeDrawerStack(this.drw_modeImplementations);

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
        GUIConnector gui = new JavaFXGui(frets, sheetNotes, this.btn_replay, this.stPn_popUp);

        this.stPn_popUp.setDisable(true);
        this.flowOrganizer = new FlowOrganizer(gui, new AudioConverter(),
                Mode.values()[this.pgn_modes.getCurrentPageIndex()]);

        // init help
        this.flowOrganizer.displayDescription();
    }


    // --- initializing gui with textures / buttons ---

    /**
     * Initializes the given button with a given logo
     *
     * @param btn  button to be initialized
     * @param icon icon to be put on the button
     */
    private void initMainButtons(JFXButton btn, FontAwesomeIcon icon) {
        initMainButtons(btn, MAIN_BTN_FONT_SIZE, icon, MAIN_ICON_FONT_SIZE);
    }

    /**
     * Initializes a given button with a given font size, icon and icon size
     *
     * @param btn      button to be initialized
     * @param fontSize font size of the text on the buttons
     * @param icon     icon to be set
     * @param iconSize size of the icon
     */
    private void initMainButtons(JFXButton btn, int fontSize, FontAwesomeIcon icon, int iconSize) {
        String cssFormat = String.format("-fx-background-color: #d6e1fc;\n"
                + "-fx-font-family: \"Forte\";\n"
                + "-fx-graphic-text-gap: 15;\n"
                + "-fx-font-size: %d;", fontSize);
        btn.setStyle(cssFormat);
        btn.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        btn.setButtonType(JFXButton.ButtonType.RAISED);
        btn.setRipplerFill(Paint.valueOf("#ffffff"));
        GlyphIcon glypIcon = GlyphsBuilder.create(FontAwesomeIconView.class)
                .glyph(icon)
                .build();
        glypIcon.setSize(String.format("%sem", iconSize));
        btn.setGraphic(glypIcon);
    }

    /**
     * Initializes the reset button
     *
     * @param btn  buttonto initialize
     * @param icon icon to be set on the button
     */
    private void initSwitchModeButton(JFXButton btn, FontAwesomeIcon icon) {
        btn.setStyle(
                "-fx-background-color: transparent;\n"
                        + "-fx-font-family: \"Forte\";\n"
                        + "-fx-graphic-text-gap: 15;\n"
                        + "-fx-font-size: 28;");
        btn.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        btn.setButtonType(JFXButton.ButtonType.FLAT);
        btn.setRipplerFill(Paint.valueOf("#ffffff"));
        GlyphIcon glypIcon = GlyphsBuilder.create(FontAwesomeIconView.class)
                .glyph(icon)
                .build();
        glypIcon.setSize("3em");
        btn.setGraphic(glypIcon);
        Paint ripplerCol = new Color(1, 1, 1, 0);
        btn.setRipplerFill(ripplerCol);
    }

    /**
     * Initializes the guitar fret texture
     */
    private void initGuitarTexture() {
        double parentWidth = this.imgParent.getBoundsInParent().getWidth();
        double parentHeight = this.imgParent.getBoundsInParent().getHeight();
        this.imgBase.setImage(new Image(FRETBOARD_TEXUTURE_PATH, parentWidth, parentHeight, true, true));
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
     * Initializes the clef texture
     */
    private void initClefTexture() {
        this.imgVw_clefTexture.setImage(new Image(CLEF_TEXTURE_PATH));
    }

    /**
     * Loads up all transparent buttons on the fretboard
     */
    private void initFrets() {
        for (int i = 0; i < Guitar.GUITAR_STRING_CNT; i++) {
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
        this.selectedModeCategory = Category.values()[0];
        this.pgn_modes.setPageCount(this.selectedModeCategory.getModes().size());
        this.pgn_modes.setPageFactory((Integer pageIdx) -> changeMode(pageIdx));
    }


    // --- actual methods needed for the interaction with the logic ---

    /**
     * Page factory for the pagination
     *
     * @param idx index of the page
     * @return page for the given index
     */
    private Label changeMode(Integer idx) {
        this.flowOrganizer.interpretMode(this.selectedModeCategory.getModes().get(idx));
        System.out.println(this.selectedModeCategory);

        Label label = new Label(this.selectedModeCategory.getModes().get(idx).getDescr());
        label.setWrapText(true);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setFont(new Font("Forte", 22));
        return label;
    }

    /**
     * User presses / selects a note on the sheet page
     *
     * @param event Action event called by the user / gui
     */
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
     * Checks in the solution which the user selected on the gui
     */
    @FXML
    public void checkInSolution() {
        this.flowOrganizer.checkInResult();
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

    void displayDescription(ActionEvent event) {
        this.flowOrganizer.displayDescription();
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

    /**
     * Resets the notes on the guitar / sheet page
     *
     * @param event Action event called by the user / gui
     */
    @FXML
    private void reset(ActionEvent event) {
        this.flowOrganizer.reset();
    }

    /**
     * Replays the excercise
     *
     * @param event Action event called by the user / gui
     */
    @FXML
    private void replayExcercise(ActionEvent event) {
        this.flowOrganizer.playExcercise();
    }

    /**
     * Switches the overal game mode of the program
     *
     * @param event event triggered by the user
     */
    @FXML
    public void switchOverallMode(ActionEvent event) {
        iterateMainDrawer(event);
        iterateModeImplementationDrawer(event);
    }

    /**
     * Initializes the second / inner drawer containing the buttons used to select an overall selectedModeCategory
     * e.g. FREEPLAY, HEARING, TRANSLATING
     *
     * @param modeDrawer drawer instance to update
     */
    private void initModeDrawerStack(JFXDrawer modeDrawer) {
        Category[] allCategories = Category.values();
        JFXButton[] btnModes = new JFXButton[allCategories.length];
        for (int i = 0; i < allCategories.length; i++) {
            btnModes[i] = initModeBtn(allCategories[i]);
            btnModes[i].setStyle("-fx-background-color: #d6e1fc;\n"
                    + "-fx-font-family: \"Forte\";\n"
                    + "-fx-graphic-text-gap: 15;\n"
                    + "-fx-font-size: 25");
            btnModes[i].setAlignment(javafx.geometry.Pos.CENTER_LEFT);
            btnModes[i].setButtonType(JFXButton.ButtonType.RAISED);
            btnModes[i].setRipplerFill(Paint.valueOf("#ffffff"));
            btnModes[i].setMinWidth(200.0);
            btnModes[i].setOpacity(1.0);
        }
        VBox vbox = new VBox(btnModes);

        vbox.setStyle("-fx-background-color: #e4f1ff");

        modeDrawer.setStyle("-fx-fill: #d48e2c");
        modeDrawer.setSidePane(vbox);
        modeDrawer.close();
        modeDrawer.setDisable(true);
    }

    /**
     * Generates a button to the corresponding category
     *
     * @param category category to which the corresponding button should be generated
     * @return a button to the corresponding category
     */
    private JFXButton initModeBtn(Category category) {
        JFXButton outputBtn = new JFXButton("  " + category.getName());
        outputBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                refreshPagination(category);
                flowOrganizer.interpretMode(category.getModes().get(pgn_modes.getCurrentPageIndex()));
                iterateMainDrawer(new ActionEvent());
                iterateModeImplementationDrawer(new ActionEvent());
            }
        });
        return outputBtn;
    }

    /**
     * Refreshes the pagination by setting the global variable holding the current mode. This variable is used
     * when creating a new page in the page factory of the pagination object.
     *
     * @param category category to be displayed on the pagination component of the gui
     */
    private void refreshPagination(Category category) {
        selectedModeCategory = category;
        pgn_modes.setPageCount(category.getModes().size());
        pgn_modes.setCurrentPageIndex((pgn_modes.getCurrentPageIndex() + 1) % pgn_modes.getPageCount());
    }

    /**
     * Initializes the main / outer drawer containing the menu items
     *
     * @param mainDrawer drawer instance to update
     */
    private void initMainDrawer(JFXDrawer mainDrawer) {
        List<JFXButton> buttons = new LinkedList<>();
        JFXButton btnOveralMode = new JFXButton("Mode");
        btnOveralMode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                drw_modeImplementations.setDisable(false);
                drw_modeImplementations.open();
            }
        });
        buttons.add(btnOveralMode);
        initMainButtons(btnOveralMode, 25, FontAwesomeIcon.EXCHANGE, 3);
        btnOveralMode.setMinWidth(200.00);

        JFXButton btnHelp = new JFXButton("Help");
        btnHelp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setMenuState(false);
                flowOrganizer.displayDescription();
            }
        });
        buttons.add(btnHelp);
        initMainButtons(btnHelp, 25, FontAwesomeIcon.INFO_CIRCLE, 3);
        btnHelp.setMinWidth(200.00);

        JFXButton btnOpenRepo = new JFXButton("Github");
        btnOpenRepo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openRepo(new ActionEvent());
            }
        });
        buttons.add(btnOpenRepo);
        initMainButtons(btnOpenRepo, 25, FontAwesomeIcon.GITHUB, 3);
        btnOpenRepo.setMinWidth(200.00);

        JFXButton btnClose = new JFXButton("Close");
        btnClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("close");
                endProgramm(event);
            }
        });
        buttons.add(btnClose);
        initMainButtons(btnClose, 25, FontAwesomeIcon.EXCLAMATION_TRIANGLE, 3);
        btnClose.setMinWidth(200.00);

        VBox drawerContent = new VBox(buttons.toArray(new JFXButton[0]));
        drawerContent.setStyle("-fx-background-color: #eaebff");
        mainDrawer.setSidePane(drawerContent);

        mainDrawer.close();
        mainDrawer.setDisable(true);
    }

    /**
     * Opcens / closes the main / outer drawer
     *
     * @param event event triggered by the user
     */
    @FXML
    public void iterateMainDrawer(Event event) {
        setMenuState(this.drw_mainMenu.isClosed());
    }

    /**
     * Sets the munu state according to the given flag
     *
     * @param state flag after which both drawers will be set
     */
    private void setMenuState(boolean state) {
        if (state) {
            this.drw_mainMenu.setDisable(false);
            this.drw_mainMenu.open();
            this.stPn_popUp.setDisable(false);
        } else {
            this.drw_mainMenu.setDisable(true);
            this.drw_mainMenu.close();
            this.stPn_popUp.setDisable(true);
        }
    }

    /**
     * Opens / closes the inner drawer
     *
     * @param event event triggered by the user
     */
    private void iterateModeImplementationDrawer(Event event) {
        setModeImplState(this.drw_modeImplementations.isClosed());
    }

    /**
     * Sets the inner drawer in a closed / open state depending on the given flag
     *
     * @param state state to which the drawer should be transformed
     */
    private void setModeImplState(boolean state) {
        if (state) {
            this.drw_modeImplementations.setDisable(false);
            this.drw_modeImplementations.open();
        } else {
            this.drw_modeImplementations.setDisable(true);
            this.drw_modeImplementations.close();
            this.stPn_popUp.setDisable(true);
        }
    }

    /**
     * Returns to the normal game window (closing the drawers)
     *
     * @param event event triggered by the user
     */
    @FXML
    public void backToWindow(Event event) {
        System.out.println("back to window");
        setMenuState(false);
        setModeImplState(false);
    }
}
