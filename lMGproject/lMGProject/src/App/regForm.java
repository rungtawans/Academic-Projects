package App;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;

import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class regForm implements Initializable {
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txt_username;

    @FXML
    private TextField txt_password;

    @FXML
    private PasswordField txt_confirmPsw;


    @FXML
    private TextField txt_email;

    
    @Override
    public void initialize(URL location, ResourceBundle resourceBundle){

    }
    
    public void SWtoLogin() throws IOException{
        App.setRoot("loginForm");
    }

    public void SWtoHome() throws IOException{
        App.setRoot("main");
    }
    
    @FXML
    void createAccount() {
        if(txt_password.getText().equalsIgnoreCase(txt_confirmPsw.getText())) {
            EditdataBase.reg(txt_username.getText(), txt_password.getText(), txt_email.getText());
            JOptionPane.showMessageDialog(null,"create account success");
        }else{
            JOptionPane.showMessageDialog(null, "Your password Not Match!","Error!",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
}