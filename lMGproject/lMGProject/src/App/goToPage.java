package App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class goToPage {
    @FXML
    private Button buttonMain;

    @FXML
    private Button buttonPage1;

    @FXML
    private Button buttonPage2;

    @FXML
    private Button buttonPage3;

    @FXML
    private Button buttonPage4;

    @FXML
    void GoToMain(ActionEvent event) throws IOException {
        App.setRoot("main");
    }

    @FXML
    void GoToPage1(ActionEvent event) throws IOException{
        App.setRoot("page1");
    }

    @FXML
    void GoToPage2(ActionEvent event) throws IOException{
        App.setRoot("page2");
    }

    @FXML
    void GoToPage3(ActionEvent event) throws IOException{
        App.setRoot("page3");
    }

    @FXML
    void GoToPage4(ActionEvent event) throws IOException{
        App.setRoot("page4");
    }
}
