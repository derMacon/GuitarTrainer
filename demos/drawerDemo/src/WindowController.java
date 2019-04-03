import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class WindowController implements Initializable {

    @FXML
    private JFXDrawersStack drawerStack;

    private VBox box;

    @FXML
    private JFXDrawer secondaryDrawer;

    @FXML
    private JFXDrawer mainDrawer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainDrawer.setSidePane(generateMainContent());
        mainDrawer.open();

        secondaryDrawer.setSidePane();
    }

    private VBox generateMainContent() { JFXButton btn1 = new JFXButton("btn1");
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                secondaryDrawer.open();
            }
        });
        JFXButton btn2 = new JFXButton("btn2");
        return new VBox(btn1, btn2);
    }

    @FXML
    public void showDrawer(ActionEvent event) {
        System.out.println("bis hier");
        if (this.secondaryDrawer.isClosed()) {
            secondaryDrawer.open();
        } else {
            secondaryDrawer.close();
        }
    }

    @FXML
    void backToWindow(Event event) {
        System.out.println("Back to window");

    }
}
