package App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class main {
    @FXML
    private Button buttonMain;

    @FXML
    private Button buttonMain1;

    @FXML
    private Button buttonPage1;

    public void SWtoLogin() throws IOException {
        App.setRoot("loginForm");
    }

    @FXML
    void GoToMain(ActionEvent event) throws IOException {
        App.setRoot("main");
    }

    @FXML
    void GoToPage1(ActionEvent event) throws IOException {
        App.setRoot("page1");
    }
}
